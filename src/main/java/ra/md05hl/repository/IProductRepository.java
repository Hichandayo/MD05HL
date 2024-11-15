package ra.md05hl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.md05hl.model.entity.Product;

public interface IProductRepository extends JpaRepository<Product,Integer> {

}
