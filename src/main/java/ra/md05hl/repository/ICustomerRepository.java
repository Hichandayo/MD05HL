package ra.md05hl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ra.md05hl.model.entity.Customer;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
    //Tìm tất cả các khách hàng có số lượng hợp đồng trên 5
    @Query("SELECT c  FROM Customer c  WHERE (SELECT COUNT(co) FROM Contract co WHERE co.id = c.id) > 5")
    List<Customer> findAllCustomerHave5Contract(Integer id);
    //Lấy danh sách các khách hàng và số lượng các tương tác với mỗi khách hàng trong tháng hiện tại

}
