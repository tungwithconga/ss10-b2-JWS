// UserRepository.java
package ra.ss10b2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.ss10b2.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
