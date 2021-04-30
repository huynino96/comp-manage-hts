package com.example.compmanage.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

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
    @JsonBackReference
    @JoinColumn(name = "cpu_id")
    private Cpu cpu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mainboard_id")
    @JsonBackReference
    private Mainboard mainboard;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ram_id")
    @JsonBackReference
    private Ram ram;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disk_id")
    @JsonBackReference
    private Disk disk;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gpu_id")
    @JsonBackReference
    private Gpu gpu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "keyboard_id")
    @JsonBackReference
    private Keyboard keyboard;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mouse_id")
    @JsonBackReference
    private Mouse mouse;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monitor_id")
    @JsonBackReference
    private Monitor monitor;
}
