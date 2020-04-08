package team404.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import team404.project.model.Debt;
import team404.project.model.details.UserDetailsImpl;
import team404.project.service.interfaces.DebtPrioritySorter;
import team404.project.service.interfaces.DebtService;
import team404.project.service.interfaces.FriendRequestService;
import team404.project.service.interfaces.UserService;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    FriendRequestService friendRequestService;
    @Autowired
    DebtService debtService;
    @Autowired
    DebtPrioritySorter debtPrioritySorter;

    @GetMapping("/profile")
    public ModelAndView getProfile(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", userService.getById(userDetails.getUser().getId()));
        modelAndView.addObject("friendRequests", friendRequestService.getByReceiver(userDetails.getUser()));
        modelAndView.addObject("oldestDebts", debtService.findOldestDebtByDebtor(userDetails.getUser(), 3));
        modelAndView.addObject("maxDebtCounts", debtService.findMaxDebtCountByDebtor(userDetails.getUser(), 3));
        modelAndView.addObject("minDebtCounts", debtService.findMinDebtCountByDebtor(userDetails.getUser(), 3));
        List<Debt> debts = debtPrioritySorter.sortByPriority(debtService.getByDebtor(userDetails.getUser()), userDetails.getUser());
        modelAndView.addObject("priorityDebts", debts.subList(0, Math.min(debts.size(), 13)));
        return modelAndView;
    }
}
