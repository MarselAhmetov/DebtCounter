package team404.project.service.interfaces;


import team404.project.model.Debt;
import team404.project.model.User;

import java.util.List;

public interface DebtPrioritySorter {
    List<Debt> sortByPriority(List<Debt> debts, User debtor);
}
