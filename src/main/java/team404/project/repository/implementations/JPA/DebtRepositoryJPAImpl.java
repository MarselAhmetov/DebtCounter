package team404.project.repository.implementations.JPA;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team404.project.model.Debt;
import team404.project.model.User;
import team404.project.repository.interfaces.DebtRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DebtRepositoryJPAImpl implements DebtRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Debt debt) {
        entityManager.persist(debt);
    }

    @Override
    public void saveAll(List<Debt> debts) {

    }

    @Override
    public Optional<Debt> findById(Integer id) {


        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer var1) {
        return false;
    }

    @Override
    public Iterable<Debt> findAll() {
        return entityManager.createQuery("from Debt d join fetch d.debtor", Debt.class).getResultList();
    }

    @Override
    public Iterable<Debt> findAllById(Iterable<Integer> var1) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(Debt var1) {

    }

    @Override
    public void deleteAll(Iterable<? extends Debt> var1) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Debt> getAllByOwner(User owner) {
        Query query = entityManager.createQuery("from Debt d join fetch d.debtor where d.owner = :owner", Debt.class);
        query.setParameter("owner", owner);
        return query.getResultList();
    }

    @Override
    public List<Debt> getAllByDebtor(User debtor) {
        Query query = entityManager.createQuery("from Debt d join fetch d.debtor where d.debtor = :debtor", Debt.class);
        query.setParameter("debtor", debtor);
        return query.getResultList();
    }

    @Override
    public List<Debt> findMinDebtCount(User debtor, Integer limit) {
        Query query = entityManager.createQuery("from Debt d join fetch d.debtor where d.debtor = :debtor order by d.debtCount asc", Debt.class).setMaxResults(limit);
        query.setParameter("debtor", debtor);
        return query.getResultList();
    }

    @Override
    public List<Debt> findMaxDebtCount(User debtor, Integer limit) {
        Query query = entityManager.createQuery("from Debt d join fetch d.debtor where d.debtor = :debtor order by d.debtCount desc", Debt.class).setMaxResults(limit);
        query.setParameter("debtor", debtor);
        return query.getResultList();
    }

    @Override
    public List<Debt> findOldestDebt(User debtor, Integer limit) {
        Query query = entityManager.createQuery("from Debt d join fetch d.debtor where d.debtor = :debtor order by d.date", Debt.class).setMaxResults(limit);
        query.setParameter("debtor", debtor);
        return query.getResultList();
    }

    @Override
    public List<Object[]> findCountByDebtor(User debtor) {
        Query query = entityManager.createQuery("select d.owner, count(*) from Debt d where d.debtor = :debtor group by d.owner");
        query.setParameter("debtor", debtor);
        return (List<Object[]>) query.getResultList();
    }
}
