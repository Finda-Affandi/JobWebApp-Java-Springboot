package com.fn.job.joby.Repositories;

import com.fn.job.joby.Model.Applicant;
import com.fn.job.joby.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    List<Applicant> findAllByAppId(Integer appId);

    List<Applicant> findAllByJobcode(Integer jobCode);

    List<Applicant> findAllByStatus(String status);
}
