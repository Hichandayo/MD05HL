package ra.md05hl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.md05hl.model.entity.Users;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String users);
}
