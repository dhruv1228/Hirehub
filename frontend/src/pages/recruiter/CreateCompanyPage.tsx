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
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useEffect, useState } from "react";
import { getMyCompany } from "../../services/companyService";

import {
  companySchema,
  type CompanyFormData,
} from "../../validation/companySchema";

import { createCompany } from "../../services/companyService";

export default function CreateCompanyPage() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<CompanyFormData>({
    resolver: zodResolver(companySchema),
  });

  useEffect(() => {
  checkCompany();
}, []);

async function checkCompany() {
  try {
    const company = await getMyCompany();

    if (company) {
      navigate("/recruiter/company");
      return;
    }
  } catch {
    // Recruiter has no company yet
  } finally {
    setLoading(false);
  }
}

  const onSubmit = async (data: CompanyFormData) => {
    try {
      await createCompany(data);

      alert("Company created successfully");

      navigate("/recruiter/dashboard");
    } catch (error) {
      console.error(error);
      alert("Failed to create company");
    }
  };
  if (loading) {
  return null;
}

  return (
    <Container maxWidth="md">
      <Box
        component="form"
        onSubmit={handleSubmit(onSubmit)}
        sx={{ mt: 5 }}
      >
        <Paper elevation={4} sx={{ p: 4 }}>
          <Typography variant="h4" gutterBottom>
            Create Company
          </Typography>

          <TextField
            label="Company Name"
            fullWidth
            margin="normal"
            {...register("name")}
            error={!!errors.name}
            helperText={errors.name?.message}
          />

          <TextField
            label="Industry"
            fullWidth
            margin="normal"
            {...register("industry")}
            error={!!errors.industry}
            helperText={errors.industry?.message}
          />

          <TextField
            label="Website"
            fullWidth
            margin="normal"
            {...register("website")}
            error={!!errors.website}
            helperText={errors.website?.message}
          />

          <TextField
            label="Headquarters"
            fullWidth
            margin="normal"
            {...register("headquarters")}
            error={!!errors.headquarters}
            helperText={errors.headquarters?.message}
          />

          <TextField
            select
            label="Company Size"
            fullWidth
            margin="normal"
            defaultValue=""
            {...register("companySize")}
            error={!!errors.companySize}
            helperText={errors.companySize?.message}
          >
            <MenuItem value="STARTUP">Startup (1-10)</MenuItem>
            <MenuItem value="SMALL">Small (11-50)</MenuItem>
            <MenuItem value="MEDIUM">Medium (51-200)</MenuItem>
            <MenuItem value="LARGE">Large (201-1000)</MenuItem>
            <MenuItem value="ENTERPRISE">Enterprise (1000+)</MenuItem>
          </TextField>

          <TextField
            label="Description"
            fullWidth
            multiline
            rows={4}
            margin="normal"
            {...register("description")}
            error={!!errors.description}
            helperText={errors.description?.message}
          />

          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{ mt: 3 }}
          >
            Create Company
          </Button>
        </Paper>
      </Box>
    </Container>
  );
}