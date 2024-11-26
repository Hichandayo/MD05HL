package ra.md05hl.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.md05hl.model.entity.Categories;

public interface ICategoryRepository extends JpaRepository<Categories, Long> {
    @Query("SELECT c FROM Categories c WHERE c.name LIKE %:name%")
    Page<Categories> searchByName(@Param("name") String name, Pageable pageable);
    boolean existsByName(String name);

}
