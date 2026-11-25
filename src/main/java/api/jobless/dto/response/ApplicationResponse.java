package api.jobless.dto.response;

import api.jobless.enums.Accomodation;
import api.jobless.enums.ApplicationStatus;

import java.util.Set;

public record ApplicationResponse(

        Long id,
        Long jobId,
        ApplicationStatus status,
        String fullName,
        String email,
        String phone,
        String coverLetter,
        Set<String> skills,
        Set<Accomodation> requestedAccommodations

) {
}
