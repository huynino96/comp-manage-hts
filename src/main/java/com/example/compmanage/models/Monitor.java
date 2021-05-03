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
@Table(name = "monitors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monitor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "monitorBrand")
    private String monitorBrand;

    @Column(name = "monitorSize")
    private String monitorSize;

//    @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "computer_id", nullable = true)
//    private Computer computer;
}
