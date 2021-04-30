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
@Table(name = "mouses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mouseName")
    private String mouseName;

    @Column(name = "mouseType")
    private String mouseType;

//    @OneToMany(mappedBy = "mouse", fetch = FetchType.EAGER)
//    @Fetch(value = FetchMode.SUBSELECT)
//    private List<Computer> computer;
}
