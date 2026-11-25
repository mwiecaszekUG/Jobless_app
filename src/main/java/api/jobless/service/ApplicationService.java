package api.jobless.service;

import api.jobless.dto.request.SubmitApplicationRequest;
import api.jobless.dto.request.UpdateApplicationStatusRequest;
import api.jobless.dto.response.ApplicationResponse;
import api.jobless.exception.ResourceNotFoundException;
import api.jobless.model.Job;
import api.jobless.model.JobApplication;
import api.jobless.repository.ApplicationRepository;
import api.jobless.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    @Transactional(readOnly = true)
    public List<ApplicationResponse> getApplicationsByJobId(Long jobId) {
        if (!jobRepository.existsById(jobId)) {
            throw new ResourceNotFoundException("Job with ID " + jobId + " not found");
        }

        return applicationRepository.findAllByJob_IdOrderByIdAsc(jobId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ApplicationResponse getApplicationById(Long applicationId) {
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application with ID " + applicationId + " not found"
                ));

        return mapToResponse(application);
    }

    @Transactional
    public ApplicationResponse updateApplicationStatus(
            Long applicationId,
            UpdateApplicationStatusRequest request) {
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application with ID " + applicationId + " not found"
                ));

        application.setStatus(request.status());
        return mapToResponse(application);
    }

    @Transactional
    public ApplicationResponse submitApplication(SubmitApplicationRequest request) {
        Job job = jobRepository.findById(request.jobId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Job with ID " + request.jobId() + " not found"
                ));

        JobApplication application = new JobApplication();
        application.setJob(job);
        application.setFullName(request.fullName());
        application.setEmail(request.email());
        application.setPhone(request.phone());
        application.setCoverLetter(request.coverLetter());
        application.setSkills(new HashSet<>(request.skills()));
        application.setRequestedAccommodations(
                new HashSet<>(request.requestedAccommodations())
        );

        JobApplication savedApplication = applicationRepository.save(application);

        return mapToResponse(savedApplication);
    }

    private ApplicationResponse mapToResponse(JobApplication application) {
        return new ApplicationResponse(
                application.getId(),
                application.getJob().getId(),
                application.getStatus(),
                application.getFullName(),
                application.getEmail(),
                application.getPhone(),
                application.getCoverLetter(),
                Set.copyOf(application.getSkills()),
                Set.copyOf(application.getRequestedAccommodations())
        );
    }
}
