package ra.md05hl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.md05hl.model.entity.Employee;
import ra.md05hl.repository.IEmployeeRepo;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private IEmployeeRepo employeeRepo;

    @Transactional
    public void save(Employee employee) {
        employeeRepo.save(employee);
    }
    public Employee findById(Long id) {
        return employeeRepo.findById(id);
    }
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }
    @Transactional
    public void delete(Long id){
        employeeRepo.deleteById(id);
    }
}
