package ra.ss10b2.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ra.ss10b2.entity.Order;
import ra.ss10b2.entity.User;
import ra.ss10b2.entity.UserAccount;
import ra.ss10b2.repository.OrderRepository;
import ra.ss10b2.repository.UserAccountRepository;
import ra.ss10b2.repository.UserRepository;

@Slf4j
@Service
public class PaymentService {

    private final UserRepository userRepo;
    private final OrderRepository orderRepo;
    private final UserAccountRepository accountRepo;

    public PaymentService(UserRepository userRepo, OrderRepository orderRepo, UserAccountRepository accountRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
        this.accountRepo = accountRepo;
    }

    @Transactional
    public void pay(Long orderId, Long userId, Double amount) {
        try {
            log.info("Yêu cầu thanh toán: orderId={}, userId={}, amount={}", orderId, userId, amount);

            User user = userRepo.findById(userId).orElse(null);
            if (user == null) {
                log.warn("Không tìm thấy userId={}", userId);
                return;
            }

            Order order = orderRepo.findById(orderId).orElse(null);
            if (order == null) {
                log.warn("Không tìm thấy orderId={}", orderId);
                return;
            }

            if (!order.getUser().getId().equals(userId)) {
                log.warn("Order {} không thuộc về user {}", orderId, userId);
                return;
            }

            if ("PAID".equals(order.getStatus())) {
                log.warn("Order {} đã được thanh toán trước đó", orderId);
                return;
            }

            UserAccount account = accountRepo.findByUser(user);
            if (account.getBalance() < amount) {
                log.warn("Tài khoản user {} không đủ tiền. Balance={}, Amount={}", userId, account.getBalance(), amount);
                return;
            }

            if (amount == 9999) {
                throw new RuntimeException("Đứt kết nối DB");
            }

            account.setBalance(account.getBalance() - amount);
            order.setStatus("PAID");

            accountRepo.save(account);
            orderRepo.save(order);

            log.info("Thanh toán thành công cho order {} của user {}", orderId, userId);

        } catch (Exception e) {
            log.error("Lỗi hệ thống khi thanh toán orderId={} userId={}", orderId, userId, e);
            throw e; // rollback transaction
        }
    }
}
