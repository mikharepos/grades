package controller;

import model.Grades;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.grades.GradesInterface;
import service.subjects.SubjectInterface;
import service.user.UserService;

@Controller
public class MainController {

    private final UserService userService;
    private final SubjectInterface subjectService;
    private final GradesInterface gradeService;

    public MainController(SubjectInterface subjectService, GradesInterface gradeService, UserService userService) {
        this.subjectService = subjectService;
        this.gradeService = gradeService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("user", userService.getCurrentAccount());
        return "home";
    }

    @GetMapping("/top")
    public String getTopPage(Model model) {
        model.addAttribute("user", userService.getCurrentAccount());
        return "top";
    }


    @GetMapping("/edit/subject/{id}")
    public String editSubjects(@PathVariable Integer id, Model model) {
        model.addAttribute("subject", subjectService.getSubjectById(id));
        model.addAttribute("grades", new Grades());
        model.addAttribute("allGrades", subjectService.getSubjectById(id).getGrades());
        return "editSubject";
    }

    @PostMapping("/save/subject/{id}")
    public String saveSubject(@PathVariable Integer id, Grades grade, Model model){
        subjectService.addToSubjectGrade(id, grade);
        model.addAttribute("subject", subjectService.getSubjectById(id));
        return "redirect:/edit/subject/{id}";
    }

    @GetMapping("/delete/subject/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
        return "redirect:/new/subject";
    }

    @GetMapping("/delete/grade/{id}")
    public String deleteGrade(@PathVariable Integer id) {
        //получаем ID предмета по удаленной оценке, для того что бы передать
        // в путь  @GetMapping("/edit/subject/{id}")
        int subjectId = gradeService.findGradeById(id).getSubject().getId();
        //удаляем оценку и остаемся на той же странице
        gradeService.deleteGradeById(id);
        return "redirect:/edit/subject/" + subjectId;
    }
}
