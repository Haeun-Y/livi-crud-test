package com.example.demo.domain.account.application;

import com.example.demo.domain.account.domain.Account;
import com.example.demo.domain.account.dto.AccountDto;
import com.example.demo.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class InternService {
    private final AccountRepository accountRepository;


    public InternService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDto findAccount(Long id) {
        Account account = this.findAccountEntity(id);
        return this.getAccountDto(account);
    }

    public List<AccountDto> findAllAccount() {
        List<Account> accountList= accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for(Account account : accountList) {
            accountDtoList.add(this.getAccountDto(account));
        }
        return accountDtoList;
    }
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = Account.builder()
                        .id(accountDto.getId())
                        .name(accountDto.getName())
                        .build();
        accountRepository.save(account);
        return this.findAccount(account.getId());
    }

    @Transactional
    public AccountDto deleteAccount(Long id) {
        Account account = this.findAccountEntity(id);
        accountRepository.delete(account);
        return this.getAccountDto(account);
    }

    @Transactional
    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        Account account = this.findAccountEntity(id);

        if(accountDto.getName()!=null)
            account.updateName(accountDto.getName());

        return this.getAccountDto(account);
    }

    private Account findAccountEntity(Long id) {
        return accountRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다"));
    }


    public AccountDto getAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .name(account.getName()).build();
    }


}
