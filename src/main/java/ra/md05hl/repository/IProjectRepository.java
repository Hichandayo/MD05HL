package ra.md05hl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.md05hl.model.entity.Project;

public interface IProjectRepository extends JpaRepository<Project,Integer> {
    // Lấy danh sách các dự án đang diễn ra và tên nhân viên quản lý dự án
}
