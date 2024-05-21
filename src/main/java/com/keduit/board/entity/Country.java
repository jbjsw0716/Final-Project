package com.keduit.board.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long countryId; //국가 아이디

    @Column(name = "country_name", unique = true)
    private String countryName; //국가명

    @OneToMany(mappedBy = "countryId", fetch = FetchType.LAZY)
    private List<Movie> countries;
}
