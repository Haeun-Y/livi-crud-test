package com.example.demo.domain.account.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AccountDto {
    private Long id;
    private String name;
}
