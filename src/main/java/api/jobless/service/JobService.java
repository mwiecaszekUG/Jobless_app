package api.jobless.service;

import api.jobless.dto.request.CreateJobRequest;
import api.jobless.dto.request.UpdateJobRequest;
import api.jobless.dto.response.JobResponse;
import api.jobless.exception.ResourceNotFoundException;
import api.jobless.model.Job;
import api.jobless.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // CREATE
    public JobResponse createJob(CreateJobRequest request) {

        Job job = new Job();

        job.setTitle(request.title());
        job.setCompany(request.company());
        job.setDescription(request.description());
        job.setWorkplaceType(request.workplaceType());
        job.setAccommodations(
                request.accommodations() != null
                        ? request.accommodations()
                        : Set.of()
        );

        Job savedJob = jobRepository.save(job);

        return mapToResponse(savedJob);
    }

    // READ ALL
    public List<JobResponse> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // READ BY ID
    public JobResponse getJobById(Long id) {

        Job job = findJobById(id);

        return mapToResponse(job);
    }

    // UPDATE
    @Transactional
    public JobResponse updateJob(Long id, UpdateJobRequest request) {

        Job job = findJobById(id);

        job.setTitle(request.title());
        job.setCompany(request.company());
        job.setDescription(request.description());
        job.setWorkplaceType(request.workplaceType());
        job.setAccommodations(
                request.accommodations() != null
                        ? request.accommodations()
                        : Set.of()
        );

        return mapToResponse(job);
    }

    // DELETE
    public void deleteJob(Long id) {

        Job job = findJobById(id);

        jobRepository.delete(job);
    }

    private Job findJobById(Long id) {

        return jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job with ID " + id + " not found"
                        )
                );
    }

    private JobResponse mapToResponse(Job job) {

        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getCompany(),
                job.getDescription(),
                job.getWorkplaceType(),
                job.getAccommodations()
        );
    }

}
