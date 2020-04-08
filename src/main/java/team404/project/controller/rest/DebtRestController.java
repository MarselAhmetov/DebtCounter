package team404.project.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.Debt;
import team404.project.model.details.UserDetailsImpl;
import team404.project.model.dto.DebtDto;
import team404.project.service.interfaces.DebtService;
import team404.project.service.interfaces.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class DebtRestController {

    @Autowired
    DebtService debtService;
    @Autowired
    UserService userService;


    @GetMapping("/debts")
    public ResponseEntity<List<Debt>> getDebtsPage(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<Debt> debts = debtService.getByDebtor(userDetails.getUser());
        return ResponseEntity.ok(debts);
    }

    @PostMapping("/debts")
    public ResponseEntity<Object> addDebt(HttpServletResponse httpServletResponse, Authentication authentication, DebtDto debtDto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
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
        try {
            httpServletResponse.sendRedirect("/profile");
        } catch (IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
        return ResponseEntity.ok().build();
    }
}
