package com.example.compmanage.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cpus")
@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cpu implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="cpuType")
    private String cpuType;

    @Column(name = "coreType")
    private String coreType;

    @Column(name = "cpuGen")
    private String cpuGen;

    @Column(name= "cpuSpeed")
    private String cpuSpeed;

    @OneToMany(mappedBy = "cpu", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<Computer> computer;
}
