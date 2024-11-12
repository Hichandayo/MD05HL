package ra.md05hl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ra.md05hl.model.entity.Contract;

public interface IContractRepository extends JpaRepository<Contract, Long> {
    @Query("SELECT c. from contract c")
}
