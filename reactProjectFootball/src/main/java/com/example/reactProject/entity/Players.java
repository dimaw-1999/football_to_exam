package com.example.reactProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="img")
    private String img;

    @Column(name="number")
    private String number;

    @Column(name="date")
    private String date;

    @Column(name="citizen")
    private String citizen;

    @Column(name="games")
    private String games;

    @Column(name="goals")
    private String goals;

    @Column(name="y_card")
    private String y_card;

    @Column(name="r_card")
    private String r_card;

}
