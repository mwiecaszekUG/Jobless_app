package api.jobless.repository;

import api.jobless.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findAllByJob_IdOrderByIdAsc(Long jobId);
}
