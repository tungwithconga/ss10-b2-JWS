// OrderController.java
package ra.ss10b2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.ss10b2.entity.Order;
import ra.ss10b2.entity.User;
import ra.ss10b2.repository.OrderRepository;
import ra.ss10b2.repository.UserRepository;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final UserRepository userRepo;
    private final OrderRepository orderRepo;

    public OrderController(UserRepository userRepo, OrderRepository orderRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam Long userId, @RequestParam Double totalAmount) {
        User user = userRepo.findById(userId).orElseThrow();
        Order order = new Order(null, user, totalAmount, "PENDING");
        return ResponseEntity.ok(orderRepo.save(order));
    }
}
