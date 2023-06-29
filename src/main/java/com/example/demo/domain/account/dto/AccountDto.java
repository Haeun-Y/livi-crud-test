package com.example.demo.domain.account.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AccountDto {

    @NotNull(message = "id은 필수값입니다.")
    private Long id;

    @NotBlank(message = "name은 필수값입니다.")
    private String name;

}
