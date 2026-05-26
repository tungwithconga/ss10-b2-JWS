// UserAccount.java
package ra.ss10b2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private Double balance;
}
