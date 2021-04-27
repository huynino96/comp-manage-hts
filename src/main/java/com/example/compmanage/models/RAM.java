package com.example.compmanage.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rams")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RAM implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ramBrand")
    private String ramBrand;

    @Column(name = "ramSize")
    private String ramSize;

    @Column(name = "ramType")
    private String ramType;

    @Column(name = "ramBus")
    private String ramBus;

    @OneToMany(mappedBy = "ram", cascade = {
            CascadeType.ALL
    })
    private List <Computer> computer;
}
