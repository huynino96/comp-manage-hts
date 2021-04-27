package com.example.compmanage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "mainboards")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class Mainboard implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mainboardBrand")
    private String mainboardBrand;

    @Column(name = "mainboardName")
    private String mainboardName;

    @OneToMany(mappedBy = "mainboard", cascade = {
            CascadeType.ALL
    })
    private List<Computer> computer;
}
