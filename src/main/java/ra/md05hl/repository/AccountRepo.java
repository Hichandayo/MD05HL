package ra.md05hl.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.md05hl.model.entity.Account;

import java.util.List;

@Repository
@Transactional
public class AccountRepo implements IAccountRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Account> findAll() {
        TypedQuery<Account> query = entityManager.createQuery("from Account", Account.class);
        return query.getResultList();
    }

    @Override
    public Account findById(Long id) {
        TypedQuery<Account> query = entityManager.createQuery("from Account where id = :id", Account.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Account account) {
        if (account.getId() == null) {
            entityManager.persist(account);
        } else {
            Account acc = findById(account.getId());
            acc.setFullName(account.getFullName());
            acc.setStatus(account.getStatus());
            acc.setPhone(account.getPhone());
            acc.setDob(account.getDob());
            acc.setAddress(account.getAddress());
            entityManager.merge(acc);
        }
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}
