package team404.project.service.interfaces;


import team404.project.model.Debt;
import team404.project.model.User;

import java.util.List;

public interface DebtService {
    void create(Debt debt);
    void deleteById(Integer id);
    List<Debt> getByOwner(User owner);
    List<Debt> getByDebtor(User debtor);
    List<Debt> findMinDebtCountByDebtor(User user, Integer limit);
    List<Debt> findMaxDebtCountByDebtor(User user, Integer limit);
    List<Debt> findOldestDebtByDebtor(User user, Integer limit);
    List<Object[]> findCountByDebtor(User debtor);
}
