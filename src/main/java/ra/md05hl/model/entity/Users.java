package ra.md05hl.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean status;
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;
}