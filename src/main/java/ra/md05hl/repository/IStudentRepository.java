package ra.md05hl.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ra.md05hl.model.entity.Student;

public interface IStudentRepository extends JpaRepository<Student,Integer> {
    boolean existsByPhone(String phone);
}
