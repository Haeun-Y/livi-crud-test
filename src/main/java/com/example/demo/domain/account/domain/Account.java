package com.example.demo.domain.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    public void updateName(String name) {
        this.name = name;
    }

    @Id
    private Long id;

    private String name;

}
