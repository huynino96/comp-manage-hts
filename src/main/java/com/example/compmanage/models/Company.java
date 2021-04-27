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
@Getter
@Setter
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Company implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="companyName")
    private String companyName;

    @OneToMany(mappedBy = "company", cascade = {
            CascadeType.ALL
    })
    private List<Computer> computer;

}
