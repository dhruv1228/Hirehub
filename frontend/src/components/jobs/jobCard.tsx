import {
  Card,
  CardContent,
  Typography,
  Button,
  Chip,
  Stack,
} from "@mui/material";
import { Link } from "react-router-dom";

import type { Job } from "../../types/job";

interface Props {
  job: Job;
}

export default function JobCard({ job }: Props) {
  return (
    <Card sx={{ mb: 2 }}>
      <CardContent>
        <Typography variant="h5">
          {job.title}
        </Typography>

        <Typography color="text.secondary">
          {job.companyName}
        </Typography>
        
        <Typography sx={{ mt: 1 }}>
          {job.description}
        </Typography>

        <Stack
          direction="row"
          spacing={1}
          sx={{ mt: 2 }}
        >
          <Chip label={job.location} />
          <Chip label={job.workMode} />
          <Chip label={job.jobType} />
          <Chip label={job.experience} />
        </Stack>
        

        <Typography
          variant="h6"
          sx={{ mt: 2 }}
        >
          ₹ {job.salary.toLocaleString()}
        </Typography>
        <Stack
            direction="row"
            spacing={2}
            sx={{ mt: 2 }}
        >
            <Button
                component={Link}
                to={`/candidate/jobs/${job.id}`}
                variant="contained"
            >
                View Details
            </Button>

            {job.applied && (
                <Chip
                    label="Applied"
                    color="success"
                />
            )}
        </Stack>
      </CardContent>
    </Card>
  );
}