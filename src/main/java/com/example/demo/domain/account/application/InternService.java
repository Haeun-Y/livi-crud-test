package com.example.demo.domain.account.application;

import com.example.demo.domain.account.domain.Account;
import com.example.demo.domain.account.dto.AccountDto;
import com.example.demo.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternService {

    private final AccountRepository accountRepository;

    public InternService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDto findAccount(Long id) {

        Account account = this.findAccountEntity(id);

        return this.mappingToAccountDto(account);
    }

    public List<AccountDto> findAllAccount() {

        List<Account> accountList = accountRepository.findAll();

        // TODO : 연습 삼아서 스트림을 활용하여 객체변환 해볼 것!
//        List<AccountDto> accountDtoList = new ArrayList<>();
//
//        for(Account account : accountList) {
//            accountDtoList.add(this.mappingToAccountDto(account));
//        }

        return accountList.stream()
                .map(this::mappingToAccountDto)
                .collect(Collectors.toList());
    }

    /**
     * 수정 내용
     * 리턴값 X
     */
    public void createAccount(AccountDto accountDto) {

        Account account = Account.builder()
                        .id(accountDto.getId())
                        .name(accountDto.getName())
                        .build();

        accountRepository.save(account);
    }

    @Transactional
    public void updateAccount(AccountDto accountDto) {

        Account account = this.findAccountEntity(accountDto.getId());

        /**
         * 수정 내용
         * 빈 데이터에 대한 입력에 대한 예외처리를
         * dto에서 vaildation을 통해 사전에 처리하는 방식으로 변경
         */
//        if(accountDto.getName()!=null)

        account.setName(accountDto.getName());
    }

    @Transactional
    public void deleteAccount(Long id) {
        /**
         * 수정 내용
         * id 값으로 바로 삭제하는 방식으로 변경
         * delete -> deleteById
         */
//        Account account = this.findAccountEntity(id);

        accountRepository.deleteById(id);
    }

    private Account findAccountEntity(Long id) {
        return accountRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다"));
    }


    /**
     * 수정 내용
     * public -> private 변경
     * getAccountDto -> mappingToAccountDto 네이밍 변경
     */
    private AccountDto mappingToAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .name(account.getName()).build();
    }


}
