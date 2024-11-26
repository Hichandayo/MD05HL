package ra.md05hl.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ra.md05hl.model.entity.Carts;


public interface ICartRepository extends JpaRepository<Carts, Long> {
    Carts findByUserId(Long userId);
}

