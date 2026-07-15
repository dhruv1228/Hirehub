import { useEffect, useState } from "react";

import { getMyApplications } from "../../services/applicationService";

import {
  Paper,
  Typography,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
} from "@mui/material";

export default function MyApplicationsPage() {

  const [applications, setApplications] = useState<any[]>([]);

  useEffect(() => {
    loadApplications();
  }, []);

  async function loadApplications() {
    const data = await getMyApplications();
    setApplications(data);
  }

  return (


      <Paper sx={{ p: 3 }}>

  <Typography variant="h4" gutterBottom>
    My Applications
  </Typography>

  <Table>

    <TableHead>

      <TableRow>

        <TableCell>Job</TableCell>

        <TableCell>Company</TableCell>

        <TableCell>Status</TableCell>

        <TableCell>Resume</TableCell>

      </TableRow>

    </TableHead>

    <TableBody>

  {applications.map((app) => (

    <TableRow key={app.id}>

      <TableCell>
        {app.jobTitle}
      </TableCell>

      <TableCell>
        {app.companyName}
      </TableCell>

      <TableCell>
        {app.status}
      </TableCell>

      <TableCell>

        <a
          href={app.resumeUrl}
          target="_blank"
          rel="noopener noreferrer"
        >
          View Resume
        </a>

      </TableCell>

    </TableRow>

  ))}

</TableBody>

  </Table>

</Paper>

    
  );
}