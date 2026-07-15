import { useEffect, useState } from "react";

import { getCandidateDashboard } from "../../services/dashboardService";
import type { CandidateDashboard } from "../../types/dashboard";

import {
  Grid,
  Paper,
  Typography,
} from "@mui/material";

export default function CandidateDashboardPage() {

  const [dashboard, setDashboard] = useState<CandidateDashboard | null>(null);

  useEffect(() => {
    loadDashboard();
  }, []);

  async function loadDashboard() {
    const data = await getCandidateDashboard();
    setDashboard(data);
  }

  if (!dashboard) {
    return (

        <Typography>Loading...</Typography>

    );
  }

  return (

<>
      <Typography variant="h4" gutterBottom>
        Candidate Dashboard
      </Typography>

      <Grid container spacing={3}>

  <Grid size={{ xs: 12, sm: 6, md: 3 }}>
    <Paper sx={{ p: 3, textAlign: "center" }}>
      <Typography variant="h6">
        Applied Jobs
      </Typography>

      <Typography variant="h3" color="primary">
        {dashboard.appliedJobs}
      </Typography>
    </Paper>
  </Grid>

  <Grid size={{ xs: 12, sm: 6, md: 3 }}>
    <Paper sx={{ p: 3, textAlign: "center" }}>
      <Typography variant="h6">
        Shortlisted
      </Typography>

      <Typography variant="h3" color="success.main">
        {dashboard.shortlistedJobs}
      </Typography>
    </Paper>
  </Grid>

  <Grid size={{ xs: 12, sm: 6, md: 3 }}>
    <Paper sx={{ p: 3, textAlign: "center" }}>
      <Typography variant="h6">
        Rejected
      </Typography>

      <Typography variant="h3" color="error.main">
        {dashboard.rejectedJobs}
      </Typography>
    </Paper>
  </Grid>

  <Grid size={{ xs: 12, sm: 6, md: 3 }}>
    <Paper sx={{ p: 3, textAlign: "center" }}>
      <Typography variant="h6">
        Hired
      </Typography>

      <Typography variant="h3" color="secondary">
        {dashboard.hiredJobs}
      </Typography>
    </Paper>
  </Grid>

</Grid>
</>

  );
}