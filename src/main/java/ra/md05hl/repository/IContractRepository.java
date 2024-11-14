package ra.md05hl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ra.md05hl.model.entity.Contract;

import java.util.List;

public interface IContractRepository extends JpaRepository<Contract, Integer> {
    //Lấy danh sách tất cả các hợp đồng và trạng thái của dự án liên quan
@Query("SELECT new  co.id, co.status, p.status FROM Contract co JOIN Project p ON p.contractId = co.id")
    List<Contract>ListOfAllContractsAndStatusOfRelatedProjects();
   //Tìm các hợp đồng có ít nhất một dự án đang ở trạng thái "ON"

    //Lấy danh sách các hợp đồng có giá trị lớn nhất trong từng tháng của năm hiện tại
}
