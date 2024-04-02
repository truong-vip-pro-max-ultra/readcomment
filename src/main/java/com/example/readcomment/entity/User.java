package com.example.readcomment.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uid;

    @Column
    private Date expDate;

    @Column
    private Long accountBalance;
}
