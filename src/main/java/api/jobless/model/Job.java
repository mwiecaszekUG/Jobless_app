package api.jobless.model;

import api.jobless.enums.Accomodation;
import api.jobless.enums.WorkplaceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false, length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkplaceType workplaceType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "job_accommodations",
            joinColumns = @JoinColumn(name = "job_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "accommodation")
    private Set<Accomodation> accommodations = new HashSet<>();
}
