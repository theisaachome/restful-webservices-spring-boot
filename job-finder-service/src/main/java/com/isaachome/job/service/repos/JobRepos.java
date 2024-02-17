package com.isaachome.job.service.repos;

import com.isaachome.job.service.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepos extends JpaRepository<Job,Long> {
}
