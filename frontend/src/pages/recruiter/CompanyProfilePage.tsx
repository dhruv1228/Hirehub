import { useEffect, useState } from "react";
import {
  Paper,
  Typography,
  Box,
  CircularProgress,
  Button,
} from "@mui/material";
import { useNavigate } from "react-router-dom";

import { getMyCompany } from "../../services/companyService";

export default function CompanyProfilePage() {
    const navigate = useNavigate();

  const [company, setCompany] = useState<any>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadCompany();
  }, []);

  async function loadCompany() {
    try {
      const data = await getMyCompany();
      setCompany(data);
    } finally {
      setLoading(false);
    }
  }

  if (loading) {
    return <CircularProgress />;
  }

  if (!company) {
    return (
      <Typography>
        Company not found.
      </Typography>
    );
  }

  return (
    <Paper sx={{ p: 4 }}>

      <Typography variant="h4" gutterBottom>
        {company.name}
      </Typography>

      <Typography>
        <strong>Industry:</strong> {company.industry}
      </Typography>

      <Typography>
        <strong>Website:</strong> {company.website}
      </Typography>

      <Typography>
        <strong>Headquarters:</strong> {company.headquarters}
      </Typography>

      <Typography>
        <strong>Company Size:</strong> {company.companySize}
      </Typography>

       <Box sx={{ mt: 3 }}>
        <Typography variant="h6">
          Description
        </Typography>

        <Typography>
          {company.description}
        </Typography>
      </Box>

      <Button
    variant="contained"
    sx={{ mt: 4 }}
    onClick={() => navigate("/recruiter/company/edit")}
>
    Edit Company
</Button>

    </Paper>
  );
}