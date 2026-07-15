package com.hirehub.job.specification;

import com.hirehub.job.entity.Job;
import com.hirehub.job.enums.JobType;
import com.hirehub.job.enums.WorkMode;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {

    public static Specification<Job> filterJobs(
            String title,
            String location,
            JobType jobType,
            WorkMode workMode
    ) {

        Specification<Job> spec = Specification.where(null);

        if (title != null && !title.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(
                            cb.lower(root.get("title")),
                            "%" + title.toLowerCase() + "%"
                    ));
        }

        if (location != null && !location.isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(
                            cb.lower(root.get("location")),
                            "%" + location.toLowerCase() + "%"
                    ));
        }

        if (jobType != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("jobType"), jobType));
        }

        if (workMode != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("workMode"), workMode));
        }

        return spec;
    }
}