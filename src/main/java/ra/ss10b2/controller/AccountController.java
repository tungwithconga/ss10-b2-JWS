// AccountController.java
package ra.ss10b2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.ss10b2.entity.User;
import ra.ss10b2.entity.UserAccount;
import ra.ss10b2.repository.UserAccountRepository;
import ra.ss10b2.repository.UserRepository;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final UserRepository userRepo;
    private final UserAccountRepository accountRepo;

    public AccountController(UserRepository userRepo, UserAccountRepository accountRepo) {
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
    }

    @PostMapping
    public ResponseEntity<UserAccount> createAccount(@RequestParam Long userId, @RequestParam Double balance) {
        User user = userRepo.findById(userId).orElseThrow();
        UserAccount account = new UserAccount(null, user, balance);
        return ResponseEntity.ok(accountRepo.save(account));
    }
}
