package com.example.reactProject.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="liga")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Liga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="img")
    private String img;

    @Column(name="description")
    private String description;

    @Column(name="country")
    private String country;

    @Column(name="year")
    private int year;

    @Column(name="score")
    private long score;

    @Column(name="budjet")
    private long budjet;

    @Column(name="bombardir")
    private String bombardir;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Club> clubs;

}
