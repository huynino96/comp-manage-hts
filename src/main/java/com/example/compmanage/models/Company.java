package com.example.compmanage.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

//import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
//@Accessors(chain = true)
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Computer> computer;
}
