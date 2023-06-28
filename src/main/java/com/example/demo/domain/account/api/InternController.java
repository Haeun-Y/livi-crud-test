package com.example.demo.domain.account.api;

import com.example.demo.domain.account.application.InternService;
import com.example.demo.domain.account.dto.AccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intern")
public class InternController {

    private final InternService internService;

    public InternController(InternService internService) {
        this.internService = internService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok().body(internService.findAccount(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccount() {
        return ResponseEntity.ok().body(internService.findAllAccount());
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok().body(internService.createAccount(accountDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long id) {
        return ResponseEntity.ok().body(internService.deleteAccount(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id,
                                                 @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok().body(internService.updateAccount(id, accountDto));
    }
}
