package ra.md05hl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ra.md05hl.model.entity.Employee;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
    //Tìm các nhân viên có tổng giá trị hợp đồng phụ trách trên 500,000
@Query("SELECT e FROM Employee e WHERE (SELECT GROUP BY FROM Contract co WHERE co.id = e.id) > 500000")
    List<Employee> FindEmployeesWithTotalContractValueInChargeOfOver500000();
    //Tìm các nhân viên có ít nhất 2 tương tác với khách hàng trong vòng tháng hiện hành


}
