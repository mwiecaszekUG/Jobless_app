package api.jobless.model;

import api.jobless.enums.Accomodation;
import api.jobless.enums.ApplicationStatus;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.SUBMITTED;

    @Column(name = "applicant_name", nullable = false, length = 120)
    private String fullName;

    @Column(name = "applicant_email", nullable = false, length = 254)
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(name = "cover_letter", length = 5000)
    private String coverLetter;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "application_skills",
            joinColumns = @JoinColumn(name = "application_id")
    )
    @Column(name = "skill", nullable = false, length = 60)
    private Set<String> skills = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "application_requested_accommodations",
            joinColumns = @JoinColumn(name = "application_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "accommodation", nullable = false)
    private Set<Accomodation> requestedAccommodations = new HashSet<>();
}
