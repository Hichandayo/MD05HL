package ra.md05hl.model.entity;

import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;
    private DateTime interactionDate;
    @Enumerated(EnumType.STRING)
    private Type interactionType;
    private String notes;

}
