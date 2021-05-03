package com.example.compmanage.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "gpus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gpu implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "gpuBrand")
    private String gpuBrand;

    @Column(name = "gpuName")
    private String gpuName;

    @Column(name = "gpuSize")
    private String gpuSize;

    @Column(name = "gpuType")
    private String gpuType;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "computer_id", nullable = true)
    private Computer computer;
}
