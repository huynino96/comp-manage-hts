package com.example.compmanage.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="computers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Computer implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "computerName")
    private String computerName;

    @Column(name = "user")
    private String user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "computer")
    private Cpu cpu;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "computer")
    private Disk disk;
//
//    @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "computer")
//    private Gpu gpu;
//
//    @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "computer")
//    private Keyboard keyboard;
//
//    @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "computer")
//    private Mainboard mainboard;
//
//    @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "computer")
//    private Monitor monitor;
//
//    @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "computer")
//    private Mouse mouse;
//
//    @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "computer")
//    private Ram ram;
}
