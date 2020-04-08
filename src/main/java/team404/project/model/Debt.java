package team404.project.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "debt_count")
    private Long debtCount;
    private String description;
    private String debtorName;
    private LocalDate date;

    @Transient
    private Double priority;

    @ManyToOne
    private User debtor;

    @ManyToOne
    private User owner;
}
