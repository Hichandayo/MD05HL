package ra.md05hl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ra.md05hl.model.entity.Product;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Long> {
    @Query("select P from Product P where P.status=true ")
    List<Product> getListProductsByStatusIsTrue();
    boolean existsByName(String name);
}

