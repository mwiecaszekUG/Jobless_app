package api.jobless.dto.request;

import api.jobless.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateApplicationStatusRequest(

        @NotNull(message = "Application status is required")
        ApplicationStatus status

) {
}
