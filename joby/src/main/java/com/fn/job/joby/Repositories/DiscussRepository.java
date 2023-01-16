package com.fn.job.joby.Repositories;

import com.fn.job.joby.Model.Discuss;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussRepository extends JpaRepository<Discuss, Integer> {
    List<Discuss> findAllByJobId(Integer jobId);
}
