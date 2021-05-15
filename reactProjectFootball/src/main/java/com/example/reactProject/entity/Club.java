package com.example.reactProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

@Entity
@Table(name="club")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="img")
    private String img;

    @Column(name="coach")
    private String coach;

    @Column(name="name")
    private String name;

    @Column(name="games")
    private String games;

    @Column(name="wins")
    private String wins;

    @Column(name="loses")
    private String loses;

    @Column(name="same")
    private String same;

    @Column(name = "difference")
    private String difference;

    @Column(name="score")
    private int score;

    @Column(name="number")
    private int number;

    @Column(name="zona")
    private boolean zona;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Players> players;


}
