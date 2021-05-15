package com.example.reactProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="result")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="club1")
    private String club1;

    @Column(name="club2")
    private String club2;

    @Column(name="img1")
    private String img1;

    @Column(name="img2")
    private String img2;

    @Column(name="date")
    private String date;

    @Column(name="result")
    private String result;

    @Column(name="date_now")
    private LocalDateTime date_now;

    @ManyToOne(fetch = FetchType.EAGER)
    private Liga liga;


}
