package api.jobless.dto.response;

import api.jobless.enums.Accomodation;
import api.jobless.enums.WorkplaceType;

import java.util.Set;

public record JobResponse(

        Long id,
        String title,
        String company,
        String description,
        WorkplaceType workplaceType,
        Set<Accomodation> accommodations

) {
}