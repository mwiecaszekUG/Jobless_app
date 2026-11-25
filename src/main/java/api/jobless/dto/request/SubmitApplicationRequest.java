package api.jobless.dto.request;

import api.jobless.enums.Accomodation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record SubmitApplicationRequest(

        @NotNull(message = "Job ID is required")
        @Positive(message = "Job ID must be positive")
        Long jobId,

        @NotBlank(message = "Applicant name is required")
        @Size(max = 120, message = "Applicant name cannot exceed 120 characters")
        String fullName,

        @NotBlank(message = "Applicant email is required")
        @Email(message = "Applicant email must be valid")
        @Size(max = 254, message = "Applicant email cannot exceed 254 characters")
        String email,

        @Pattern(
                regexp = "^\\+?[0-9 ()-]{7,20}$",
                message = "Phone number must contain 7 to 20 valid characters"
        )
        String phone,

        @Size(max = 5000, message = "Cover letter cannot exceed 5000 characters")
        String coverLetter,

        @NotNull(message = "Skills are required; use an empty array if none")
        @Size(max = 30, message = "No more than 30 skills may be submitted")
        Set<@NotBlank(message = "Skill cannot be blank")
                @Size(max = 60, message = "A skill cannot exceed 60 characters") String> skills,

        @NotNull(message = "Requested accommodations are required")
        Set<@NotNull(message = "Accommodation cannot be null") Accomodation> requestedAccommodations

) {
}
