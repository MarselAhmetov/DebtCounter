package team404.project.controller.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.dto.SignUpDto;
import team404.project.service.interfaces.SignUpService;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @GetMapping("/signUp")
    public ModelAndView getSignUpPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("signUpDto", new SignUpDto());
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(@Valid SignUpDto form, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("signup");
//            modelAndView.addObject("error", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        signUpService.signUp(form);
        modelAndView.setViewName("redirect:/signIn");
        return modelAndView;
    }
}
