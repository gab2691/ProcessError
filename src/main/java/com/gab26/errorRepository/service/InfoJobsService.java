package com.gab26.errorRepository.service;

import com.gab26.errorRepository.model.InfoJobs;
import com.gab26.errorRepository.repository.InfoJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoJobsService{

    @Autowired
    private InfoJobsRepository infoJobsRepository;

    public InfoJobs findByJob(String job) {
        return infoJobsRepository.findByJob(job);
    }


    public InfoJobs save(InfoJobs infoJobs) {
        return infoJobsRepository.save(infoJobs);
    }
}
