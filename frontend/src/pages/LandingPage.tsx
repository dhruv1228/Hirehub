import {
  Box,
  Button,
  Container,
  Stack,
  Typography,
  Paper,
  Grid
} from "@mui/material";
import {
  AppBar,
  Toolbar
} from "@mui/material";
import { useNavigate } from "react-router-dom";

import { useEffect, useState } from "react";
import { getHomeStats } from "../services/dashboardService";
import type { HomeStats } from "../types/homeStats";

export default function LandingPage() {
    const [stats, setStats] = useState<HomeStats>({
    jobs: 0,
    companies: 0,
    candidates: 0,
    recruiters: 0,
    });
        useEffect(() => {
        loadStats();
        }, []);

        async function loadStats() {
        try {
            const data = await getHomeStats();
            setStats(data);
        } catch (err) {
            console.error(err);
        }
        }
  const navigate = useNavigate();

  return (
  <>
    <AppBar position="static" color="transparent" elevation={0}>
      <Toolbar>
        <Typography
          variant="h5"
          sx={{
            flexGrow: 1,
            fontWeight: "bold",
          }}
        >
          HireHub
        </Typography>

        <Button onClick={() => navigate("/login")}>
          Login
        </Button>

        <Button
          variant="contained"
          onClick={() => navigate("/register")}
        >
          Register
        </Button>
      </Toolbar>
    </AppBar>

    <Container maxWidth="lg">

      <Box
  sx={{
    minHeight: "80vh",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    gap: 6,
    flexWrap: "wrap",
  }}
>
  <Box
  sx={{
    flex: 1,
  }}
>
    <Typography
      variant="h2"
      sx={{
        fontWeight: "bold",
        mb: 3,
      }}
    >
      Find Your Dream Job Today
    </Typography>

    <Typography
      variant="h6"
      color="text.secondary"
      sx={{ mb: 4 }}
    >
      HireHub connects talented candidates with top recruiters across
      thousands of companies.
    </Typography>

    <Stack direction="row" spacing={2}>
      <Button
        variant="contained"
        size="large"
        onClick={() => navigate("/jobs")}
      >
        Browse Jobs
      </Button>

      <Button
        variant="outlined"
        size="large"
        onClick={() => navigate("/register")}
      >
        Get Started
      </Button>
    </Stack>
  </Box>

  <Box
  sx={{
    flex: 1,
    display: "flex",
    justifyContent: "center",
  }}
>
    <img
      src="https://images.unsplash.com/photo-1521737604893-d14cc237f11d?w=700"
      alt="Team working"
      style={{
        width: "100%",
        maxWidth: 500,
        borderRadius: 20,
      }}
    />
  </Box>
</Box>
        <Box sx={{ py: 8 }}>
  <Typography
    variant="h4"
    align="center"
    sx={{ mb: 5, fontWeight: "bold" }}
  >
    HireHub in Numbers
  </Typography>

  <Grid container spacing={3}>
  <Grid size={{ xs: 12, md: 3 }}>
    <Paper sx={{ p: 4, textAlign: "center" }}>
      <Typography variant="h3">{stats.jobs}</Typography>
      <Typography>Jobs</Typography>
    </Paper>
  </Grid>

  <Grid size={{ xs: 12, md: 3 }}>
    <Paper sx={{ p: 4, textAlign: "center" }}>
      <Typography variant="h3">{stats.companies}</Typography>
      <Typography>Companies</Typography>
    </Paper>
  </Grid>

  <Grid size={{ xs: 12, md: 3 }}>
    <Paper sx={{ p: 4, textAlign: "center" }}>
      <Typography variant="h3">{stats.candidates}</Typography>
      <Typography>Candidates</Typography>
    </Paper>
  </Grid>

  <Grid size={{ xs: 12, md: 3 }}>
    <Paper sx={{ p: 4, textAlign: "center" }}>
      <Typography variant="h3">{stats.recruiters}</Typography>
      <Typography>Recruiters</Typography>
    </Paper>
  </Grid>
</Grid>
</Box>
    </Container>
  </>
);
}