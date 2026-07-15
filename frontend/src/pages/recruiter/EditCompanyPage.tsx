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
import { Controller, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useEffect, useState } from "react";
import { getMyCompany } from "../../services/companyService";
import {
  updateCompany,
} from "../../services/companyService";


import {
  companySchema,
  type CompanyFormData,
} from "../../validation/companySchema";



export default function CreateCompanyPage() {
  const navigate = useNavigate();


  const {
    register,
  control,
  handleSubmit,
  reset,
  formState: { errors },
} = useForm<CompanyFormData>({
  resolver: zodResolver(companySchema),
  defaultValues: {
    name: "",
    industry: "",
    website: "",
    headquarters: "",
    companySize: "STARTUP",
    description: "",
  },
});


useEffect(() => {
  loadCompany();
}, []);

async function loadCompany() {
  const company = await getMyCompany();

  reset({
    name: company.name,
    industry: company.industry,
    website: company.website,
    headquarters: company.headquarters,
    companySize: company.companySize,
    description: company.description,
  });
}



  const onSubmit = async (data: CompanyFormData) => {
  try {

    const company = await getMyCompany();

    await updateCompany(company.id, data);

    alert("Company updated successfully!");

    navigate("/recruiter/company");

  } catch (error) {
    console.error(error);
    alert("Failed to update company");
  }
};

  return (
    <Container maxWidth="md">
      <Box
        component="form"
        onSubmit={handleSubmit(onSubmit)}
        sx={{ mt: 5 }}
      >
        <Paper elevation={4} sx={{ p: 4 }}>
          <Typography variant="h4" gutterBottom>
            Edit Company
          </Typography>

          <Controller
            name="name"
            control={control}
            render={({ field }) => (
                <TextField
                {...field}
                fullWidth
                margin="normal"
                label="Company Name"
                error={!!errors.name}
                helperText={errors.name?.message}
                />
            )}
            />

          <Controller
            name="industry"
            control={control}
            render={({ field }) => (
                <TextField
                {...field}
                label="Industry"
                fullWidth
                margin="normal"
                error={!!errors.industry}
                helperText={errors.industry?.message}
                />
            )}
            />
          <Controller
            name="website"
            control={control}
            render={({ field }) => (
                <TextField
                {...field}
                label="Website"
                fullWidth
                margin="normal"
                error={!!errors.website}
                helperText={errors.website?.message}
                />
            )}
            />

          <Controller
            name="headquarters"
            control={control}
            render={({ field }) => (
                <TextField
                {...field}
                label="Headquarters"
                fullWidth
                margin="normal"
                error={!!errors.headquarters}
                helperText={errors.headquarters?.message}
                />
            )}
            />

          <Controller
            name="companySize"
            control={control}
            render={({ field }) => (
                <TextField
                {...field}
                select
                label="Company Size"
                fullWidth
                margin="normal"
                error={!!errors.companySize}
                helperText={errors.companySize?.message}
                >
                <MenuItem value="STARTUP">Startup (1–10)</MenuItem>
                <MenuItem value="SMALL">Small (11–50)</MenuItem>
                <MenuItem value="MEDIUM">Medium (51–200)</MenuItem>
                <MenuItem value="LARGE">Large (201–1000)</MenuItem>
                <MenuItem value="ENTERPRISE">Enterprise (1000+)</MenuItem>
                </TextField>
            )}
            />

          <Controller
            name="description"
            control={control}
            render={({ field }) => (
                <TextField
                {...field}
                label="Description"
                fullWidth
                multiline
                rows={4}
                margin="normal"
                error={!!errors.description}
                helperText={errors.description?.message}
                />
            )}
            />

          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{ mt: 3 }}
          >
            Update Company
          </Button>
        </Paper>
      </Box>
    </Container>
  );
}