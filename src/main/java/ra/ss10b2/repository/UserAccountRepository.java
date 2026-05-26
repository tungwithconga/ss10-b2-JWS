// UserAccountRepository.java
package ra.ss10b2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.ss10b2.entity.UserAccount;
import ra.ss10b2.entity.User;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUser(User user);
}
