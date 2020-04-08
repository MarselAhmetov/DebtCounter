package team404.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {

    @GetMapping("/signIn")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView("signin");
        return modelAndView;
    }
}
