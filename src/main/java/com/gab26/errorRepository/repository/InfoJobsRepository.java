package com.gab26.errorRepository.repository;

import com.gab26.errorRepository.model.InfoJobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoJobsRepository extends JpaRepository<InfoJobs, Long> {
    InfoJobs findByJob(String job);
}
