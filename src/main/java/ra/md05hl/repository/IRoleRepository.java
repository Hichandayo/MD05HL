package ra.md05hl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.md05hl.model.entity.RoleName;
import ra.md05hl.model.entity.Roles;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findByRoleName(RoleName roleName);
}
