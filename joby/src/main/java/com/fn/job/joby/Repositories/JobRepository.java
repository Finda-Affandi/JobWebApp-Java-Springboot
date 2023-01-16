package com.fn.job.joby.Repositories;

import com.fn.job.joby.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findAllByCompany(String company);

    List<Job> findAllByStatus(String status);

}
