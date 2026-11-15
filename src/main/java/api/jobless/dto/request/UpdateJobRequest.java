package api.jobless.dto.request;

import api.jobless.enums.Accomodation;
import api.jobless.enums.WorkplaceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UpdateJobRequest(

        @NotBlank(message = "Job title is required")
        @Size(max = 150, message = "Job title cannot exceed 150 characters")
        String title,

        @NotBlank(message = "Company name is required")
        @Size(max = 150, message = "Company name cannot exceed 150 characters")
        String company,

        @NotBlank(message = "Job description is required")
        @Size(max = 2000, message = "Description cannot exceed 2000 characters")
        String description,

        @NotNull(message = "Workplace type is required")
        WorkplaceType workplaceType,

        Set<Accomodation> accommodations

) {
}
