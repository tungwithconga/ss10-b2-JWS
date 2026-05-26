// OrderRepository.java
package ra.ss10b2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.ss10b2.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
