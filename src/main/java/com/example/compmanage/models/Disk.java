package com.example.compmanage.models;

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
@Table(name = "disks")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Disk implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "diskBrand")
    private String diskBrand;

    @Column(name = "diskType")
    private String diskType;

    @Column(name = "diskSize")
    private String diskSize;

//    @OneToMany(mappedBy = "disk", fetch = FetchType.EAGER)
//    @Fetch(value = FetchMode.SUBSELECT)
//    private List<Computer> computer;


}
