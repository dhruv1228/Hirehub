import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import {
  Typography,
  Paper,
  Stack,
  Chip,
  CircularProgress,
} from "@mui/material";
import { Button } from "@mui/material";
import { Link } from "react-router-dom";


import { getJobById } from "../../services/jobService";
import type { Job } from "../../types/job";

export default function JobDetailsPage() {
  const { id } = useParams();

  const [job, setJob] = useState<Job | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!id) return;

    async function loadJob() {
      try {
        const data = await getJobById(Number(id));
        setJob(data);
      } finally {
        setLoading(false);
      }
    }

    loadJob();
  }, [id]);

  if (loading) {
    return (

        <CircularProgress />

    );
  }

  if (!job) {
    return (

        <Typography>Job not found.</Typography>

    );
  }

  return (

      <Paper sx={{ p: 4 }}>
        <Typography variant="h4">
          {job.title}
        </Typography>

        <Typography color="text.secondary">
          {job.companyName}
        </Typography>

        <Stack direction="row" spacing={1} sx={{ mt: 2 }}>
          <Chip label={job.location} />
          <Chip label={job.jobType} />
          <Chip label={job.workMode} />
          <Chip label={job.experience} />
        </Stack>

        <Typography variant="h5" sx={{ mt: 3 }}>
          ₹ {job.salary.toLocaleString()}
        </Typography>

        <Typography sx={{ mt: 3 }}>
          {job.description}
        </Typography>
       <Button
        variant="contained"
        component={job.applied ? "button" : Link}
        to={job.applied ? undefined : `/candidate/jobs/${job.id}/apply`}
        disabled={job.applied}
        sx={{ mt: 3 }}
    >
        {job.applied ? "Already Applied" : "Apply Now"}
    </Button>
      </Paper>

  );
}