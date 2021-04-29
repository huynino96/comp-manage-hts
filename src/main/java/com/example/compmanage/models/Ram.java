package com.example.compmanage.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rams")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ram implements Serializable {
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

    @Column(name = "ramQuantity")
    private String ramQuantity;

    @OneToMany(mappedBy = "ram", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<Computer> computer;
}
