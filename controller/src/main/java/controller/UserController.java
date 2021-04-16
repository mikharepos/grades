package controller;

import model.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.subjects.SubjectInterface;
import service.user.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final SubjectInterface subjectInterface;

    public UserController(UserService userService, SubjectInterface subjectInterface) {
        this.userService = userService;
        this.subjectInterface = subjectInterface;
    }

    @GetMapping("/user/new/subject")
    public String newSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        model.addAttribute("allSubject", userService.getAllSubjectsByAccount(userService.getCurrentAccount()));
        return "subject";
    }

    @PostMapping("/user/add/new/subject")
    public String addSubject(Subject subject){
        userService.getCurrentAccount().getSubjects().add(subject);
        userService.accountUpdate(userService.getCurrentAccount());
        return "redirect:/user/new/subject";
    }

    @GetMapping("/user/all/Subjects")
    public String getSubjects(Model model) {
        //получаем средний бал
        subjectInterface.averageGrade(userService.getAllSubjectsByAccount(userService.getCurrentAccount()));
        model.addAttribute("allSubject", userService.getAllSubjectsByAccount(userService.getCurrentAccount()));
        return "allSubjects";
    }

    @GetMapping("/user/profile")
    public String getUserProfile(Model model){
        model.addAttribute("user", userService.getCurrentAccount());
        return "profile";
    }





}
