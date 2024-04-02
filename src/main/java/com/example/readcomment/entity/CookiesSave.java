package com.example.readcomment.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CookiesSave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String cookies;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdDate;
}
