package ra.md05hl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.md05hl.model.entity.Users;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {
    Page<Users> findByFullNameContainingOrEmailContaining(String username, String email, Pageable pageable);

    Boolean existsByEmail(String email);
    Page<Users> findAll(Pageable pageable);

    Optional<Users> findByEmail(String email);
}