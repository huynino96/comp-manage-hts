package com.example.compmanage.models;

import lombok.*;
//import lombok.experimental.Accessors;
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
@Getter
@Setter
//@Accessors(chain = true)
public class Computer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String user;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Company company;

}
