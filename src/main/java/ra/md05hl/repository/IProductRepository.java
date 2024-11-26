package ra.md05hl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.md05hl.model.dto.response.ProductDto;
import ra.md05hl.model.dto.response.ResponseDto;
import ra.md05hl.model.entity.Products;

import java.util.List;

public interface IProductRepository extends JpaRepository<Products,Long> {
    //?1 đại diện cho tham số đầu tiên của phương thức findAllProductByCategoryId, tức là categoryId.
    @Query("SELECT p FROM Products p WHERE p.categories.categoryId = ?1")
    ResponseDto<List<Products>> findAllProductByCategoryId(Long id);
    @Query("SELECT p FROM Products p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    Page<Products> searchByNameOrDescription(@Param("keyword") String keyword, Pageable pageable);
    boolean existsByName(String name);
    @Query("select P from Products P where P.status=true ")
    List<ProductDto> findAllProductsByStatusIsTrue();
}

