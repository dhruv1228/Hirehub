import { useEffect, useState } from "react";
import {
  CircularProgress,
  Typography,
  TextField,
  MenuItem,
  Grid,
  Button,
  Box,
} from "@mui/material";


import JobCard from "../../components/jobs/jobCard";

import {
  getJobs,
  filterJobs,
} from "../../services/jobService";
import type { Job } from "../../types/job";

export default function JobsPage() {
const [jobs, setJobs] = useState<Job[]>([]);
const [page, setPage] = useState(0);
const [totalPages, setTotalPages] = useState(0);
  const [title, setTitle] = useState("");
const [location, setLocation] = useState("");
const [jobType, setJobType] = useState("");
const [workMode, setWorkMode] = useState("");
  const [loading, setLoading] = useState(true);

 useEffect(() => {
  loadJobs();
}, [page]);

async function loadJobs() {
  try {

    const data = await getJobs(page, 10);

    setJobs(data.content);
    setTotalPages(data.totalPages);

  } catch (error) {
    console.error(error);
  } finally {
    setLoading(false);
  }
}

useEffect(() => {

  async function applyFilters() {

    try {

      if (
        title === "" &&
        location === "" &&
        jobType === "" &&
        workMode === ""
      ) {
        loadJobs();
        return;
      }

      const data = await filterJobs(
        title,
        location,
        jobType,
        workMode,
        page,
        10
      );

      setJobs(data.content);
      setTotalPages(data.totalPages);

    } catch (error) {
      console.error(error);
    }

  }

  const timer = setTimeout(applyFilters, 400);

  return () => clearTimeout(timer);

}, [title, location, jobType, workMode, page]);

useEffect(() => {
    setPage(0);
}, [title, location, jobType, workMode]);

  if (loading) {
    return (

        <CircularProgress />

    );
  }

  return (
<>
      <Typography variant="h4" gutterBottom>
        Available Jobs
      </Typography>
      <Grid container spacing={2} sx={{ mb: 3 }}>

  <Grid size={{ xs: 12, md: 3 }}>
    <TextField
      fullWidth
      label="Job Title"
      value={title}
      onChange={(e) => setTitle(e.target.value)}
    />
  </Grid>

  <Grid size={{ xs: 12, md: 3 }}>
    <TextField
      fullWidth
      label="Location"
      value={location}
      onChange={(e) => setLocation(e.target.value)}
    />
  </Grid>

  <Grid size={{ xs: 12, md: 3 }}>
    <TextField
      select
      fullWidth
      label="Job Type"
      value={jobType}
      onChange={(e) => setJobType(e.target.value)}
    >
      <MenuItem value="">All</MenuItem>
      <MenuItem value="FULL_TIME">Full Time</MenuItem>
      <MenuItem value="PART_TIME">Part Time</MenuItem>
      <MenuItem value="INTERNSHIP">Internship</MenuItem>
      <MenuItem value="CONTRACT">Contract</MenuItem>
    </TextField>
  </Grid>

  <Grid size={{ xs: 12, md: 3 }}>
    <TextField
      select
      fullWidth
      label="Work Mode"
      value={workMode}
      onChange={(e) => setWorkMode(e.target.value)}
    >
      <MenuItem value="">All</MenuItem>
      <MenuItem value="ONSITE">Onsite</MenuItem>
      <MenuItem value="REMOTE">Remote</MenuItem>
      <MenuItem value="HYBRID">Hybrid</MenuItem>
    </TextField>
  </Grid>

</Grid>

<Button
  variant="outlined"
  sx={{ mb: 3 }}
  onClick={() => {
    setTitle("");
    setLocation("");
    setJobType("");
    setWorkMode("");
    setPage(0);
}}
>
  Reset Filters
</Button>

      {jobs.map((job) => (
        <JobCard
          key={job.id}
          job={job}
        />
      ))}
      <div
  style={{
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    marginTop: "24px",
  }}
>

  <Button
    variant="outlined"
    disabled={page === 0}
    onClick={() => setPage((prev) => prev - 1)}
  >
    Previous
  </Button>

  <Typography>
    Page {page + 1} of {totalPages}
  </Typography>

  <Button
    variant="outlined"
    disabled={page + 1 >= totalPages}
    onClick={() => setPage((prev) => prev + 1)}
  >
    Next
  </Button>

</div>
      </>

  );
}