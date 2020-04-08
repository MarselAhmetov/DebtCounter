package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.Debt;
import team404.project.model.details.UserDetailsImpl;
import team404.project.model.dto.DebtDto;
import team404.project.service.interfaces.DebtService;
import team404.project.service.interfaces.UserService;

import java.time.LocalDate;


public class DebtController {

    @Autowired
    DebtService debtService;
    @Autowired
    UserService userService;


    @GetMapping("/debts")
    public ModelAndView getDebtsPage(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("debts", debtService.getByOwner(userDetails.getUser()));
        modelAndView.addObject("mydebts", debtService.getByDebtor(userDetails.getUser()));
        modelAndView.setViewName("debts");
        return modelAndView;
    }

    @PostMapping("/debts")
    public ModelAndView addDebt(Authentication authentication, DebtDto debtDto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile");
        if (debtDto.getWhos().equals("friend")) {
            debtService.create(Debt.builder()
                    .debtCount(debtDto.getDebtCount())
                    .debtor(userService.getById(debtDto.getFriendId()))
                    .owner(userDetails.getUser())
                    .debtorName(debtDto.getDebtorName())
                    .description(debtDto.getDescription())
                    .date(LocalDate.parse(debtDto.getDate()))
                    .build());
        } else {
            debtService.create(Debt.builder()
                    .debtCount(debtDto.getDebtCount())
                    .debtor(userDetails.getUser())
                    .owner(userService.getById(debtDto.getFriendId()))
                    .debtorName(debtDto.getDebtorName())
                    .description(debtDto.getDescription())
                    .date(LocalDate.parse(debtDto.getDate()))
                    .build());
        }
        return modelAndView;
    }
}
