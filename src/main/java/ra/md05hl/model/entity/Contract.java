package ra.md05hl.model.entity;

import com.google.type.Decimal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer cutomerId;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;
    private Date startDate;
    private Date endDate;
    private Decimal contractValue;
    @Enumerated(EnumType.STRING)
    private Status status;
}
