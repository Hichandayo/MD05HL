package ra.md05hl.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.md05hl.model.entity.Employee;

import java.util.List;
@Repository
@Transactional
public class EmployeeRepo implements IEmployeeRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(Long id) {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee where id = :id", Employee.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Employee employee) {
        if (employee.getId() == null) {
            entityManager.persist(employee);
        } else {
            Employee emp = findById(employee.getId());
            emp.setFullName(employee.getFullName());
            emp.setStatus(employee.getStatus());
            emp.setAddress(employee.getAddress());
            emp.setPhone(employee.getPhone());
            emp.setEmail(employee.getEmail());
            emp.setDob(employee.getDob());
            entityManager.merge(emp);
        }
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}