package controller;

import model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.user.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/registration")
    public String getRegistrationForm(Model model){
        model.addAttribute("account", new Account());
        return "registration";
    }

    @PostMapping("/get/registration")
    public String getRegistrationComplete(Account account, Model model){
        Account accountFromDb = userService.getAccount(account.getLogin());
        if (accountFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        userService.saveUser(account);
        return "login";
    }
}
