package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.Debt;
import team404.project.model.User;
import team404.project.service.interfaces.DebtPrioritySorter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DebtsPrioritySorterImpl implements DebtPrioritySorter {

    @Autowired
    DebtServiceImpl debtService;

    public List<Debt> sortByPriority(List<Debt> debts, User debtor) {
        List<Object[]> users = debtService.findCountByDebtor(debtor);
        Map<Integer, Long> debtCountMap = new HashMap<>();
        for (Object[] user : users) {
            debtCountMap.put(((User) user[0]).getId(), Long.parseLong(String.valueOf(user[1])));
        }
        for (Debt debt : debts) {
            System.out.println(debt);
            System.out.println(debt.getDebtCount());
            System.out.println(debtCountMap.get(debt.getOwner().getId()));
            System.out.println(Math.abs(ChronoUnit.DAYS.between(debt.getDate(), LocalDate.now())));
            debt.setPriority(debt.getDebtCount() * 1.5 * debtCountMap.get(debt.getOwner().getId()) + Math.abs(ChronoUnit.DAYS.between(debt.getDate(), LocalDate.now())) * 100);
        }
        debts.sort((o1, o2) -> o2.getPriority().compareTo(o1.getPriority()));
        return debts;
    }
}
