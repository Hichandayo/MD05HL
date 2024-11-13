package ra.md05hl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ra.md05hl.model.entity.Contract;

public interface IContractRepository extends JpaRepository<Contract, Integer> {
    //Lấy danh sách tất cả các hợp đồng và trạng thái của dự án liên quan

   //Tìm các hợp đồng có ít nhất một dự án đang ở trạng thái "ON"

    //Lấy danh sách các hợp đồng có giá trị lớn nhất trong từng tháng của năm hiện tại
}
