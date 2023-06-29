package com.example.demo.domain.account.api;

import com.example.demo.domain.account.application.InternService;
import com.example.demo.domain.account.dto.AccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/intern")
public class InternController {

    private final InternService internService;

    public InternController(InternService internService) {
        this.internService = internService;
    }

    /**
     * === 코드 리팩토링 기준 ===
     * 1. 코드 리뷰때 언급된 부분 개선했습니다.
     * 2. ELP 코드 스타일처럼 response 헤더의 Location에 URI 추가하고, read를 제외한 컨트롤러는 리턴값 없는 구조로 변경하였습니다.
     * 3. 구현된 기능을 유지하면서 디벨롭 할 수 있는 부분은 진행하였습니다.(물론, 저의 기준입니다!)
     */

    /**
     * 컨트롤러 수정 내용
     * 1. 컨트롤러 메서드 네이밍 변경 -> 서비스 메서드들과 일치하게 매핑하였음. for 일관성
     *  - getAccount -> findAccount
     *  - getAllAccount -> findAllAccount
     * 2. response header => location에 URI 추가
     * 3. read 컨트롤러를 제외한 나머지는 response 리턴값 X
     * 4. update -> 기존 id를 중복으로 받고있던 구조를 수정
     */

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findAccount(@PathVariable Long id) {
        String location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        return ResponseEntity.ok().header("Location", location).body(internService.findAccount(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> findAllAccount() {
        String location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        return ResponseEntity.ok().header("Location", location).body(internService.findAllAccount());
    }

    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody AccountDto accountDto) {
        internService.createAccount(accountDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateAccount(@Valid @RequestBody AccountDto accountDto) {
        internService.updateAccount(accountDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        internService.deleteAccount(id);
        String location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        return ResponseEntity.ok().header("Location", location).build();
    }
}
