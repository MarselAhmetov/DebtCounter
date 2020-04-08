package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import team404.project.service.interfaces.ConfirmService;

@Controller
public class ConfirmController {

    @Autowired
    ConfirmService confirmService;

    @GetMapping("/confirm")
    public ModelAndView confirm(@RequestParam("code") String code) {
        ModelAndView modelAndView = new ModelAndView();
        confirmService.confirm(code);
        modelAndView.setViewName("confirm_page");
        return modelAndView;
    }
}
