package com.example.compmanage.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="computers")
@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpu_id")
    private CPU cpu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mainboard_id")
    private Mainboard mainboard;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ram_id")
    private RAM ram;

}
