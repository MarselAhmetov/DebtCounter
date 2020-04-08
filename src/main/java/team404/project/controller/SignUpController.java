package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.dto.SignUpDto;
import team404.project.service.interfaces.SignUpService;

@Controller
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @GetMapping("/signUp")
    public ModelAndView getSignUpPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(SignUpDto form) {
        ModelAndView modelAndView = new ModelAndView();
        signUpService.signUp(form);
        modelAndView.setViewName("redirect:/signIn");
        return modelAndView;
    }
}
