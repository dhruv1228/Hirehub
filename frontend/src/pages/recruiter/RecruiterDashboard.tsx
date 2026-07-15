import {
  Box,
  Button,
  Container,
  MenuItem,
  Paper,
  TextField,
  Typography,
} from "@mui/material";

import { useNavigate } from "react-router-dom";

export default function RecruiterDashboard() {
  const navigate=useNavigate();

  return (
    <>
      <h1>Recruiter Dashboard</h1>

      <Button
        onClick={() => navigate("/recruiter/company/create")}
      >
        Create Company
      </Button>

      {/* rest of your dashboard */}
    </>
  );
}