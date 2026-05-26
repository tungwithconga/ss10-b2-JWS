package ra.ss10b2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.ss10b2.service.PaymentService;

@Slf4j
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestParam Long orderId,
                                      @RequestParam Long userId,
                                      @RequestParam Double amount) {
        try {
            paymentService.pay(orderId, userId, amount);
            return ResponseEntity.ok("Thanh toán xử lý xong");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Có lỗi xảy ra");
        }
    }
}
