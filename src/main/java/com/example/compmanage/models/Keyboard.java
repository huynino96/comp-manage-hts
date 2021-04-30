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
@Table(name = "keyboards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keyboard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "keyboardName")
    private String keyboardName;

    @Column(name = "keyboardProduct")
    private String keyboardProduct;

//    @OneToMany(mappedBy = "keyboard", fetch = FetchType.EAGER)
//    @Fetch(value = FetchMode.SUBSELECT)
//    private List<Computer> computer;
}
