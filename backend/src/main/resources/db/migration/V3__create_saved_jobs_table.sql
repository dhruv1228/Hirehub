CREATE TABLE saved_jobs (

    id BIGSERIAL PRIMARY KEY,

    candidate_id BIGINT NOT NULL,

    job_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_saved_candidate
        FOREIGN KEY(candidate_id)
        REFERENCES users(id),

    CONSTRAINT fk_saved_job
        FOREIGN KEY(job_id)
        REFERENCES jobs(id),

    CONSTRAINT uk_saved_job
        UNIQUE(candidate_id, job_id)
);