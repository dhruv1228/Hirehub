import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import DashboardLayout from "../../layouts/DashboardLayout";
import { getApplicationsByJob } from "../../services/applicationService";
import {
  updateApplicationStatus,
} from "../../services/applicationService";
import {
  Button,
} from "@mui/material";
import {
  Paper,
  Typography,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  Link,
} from "@mui/material";

export default function JobApplicationsPage() {

  const { id } = useParams();

  const [applications, setApplications] = useState<any[]>([]);

  async function handleStatus(
  applicationId: number,
  status: string
    ) {
    try {
        await updateApplicationStatus(applicationId, status);

        loadApplications();

    } catch (error) {
        console.error(error);

        alert("Failed to update status");
    }
    }

  useEffect(() => {
    loadApplications();
  }, []);

  async function loadApplications() {
    const data = await getApplicationsByJob(Number(id));
    setApplications(data);
  }

  return (


      <Paper sx={{ p: 3 }}>

        <Typography variant="h4" gutterBottom>
          Job Applications
        </Typography>

        <Table>

          <TableHead>
            <TableRow>
                <TableCell>Candidate</TableCell>
                <TableCell>Resume</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Actions</TableCell>
            </TableRow>
            </TableHead>

          <TableBody>

            {applications.map(app => (

              <TableRow key={app.id}>

                <TableCell>
                  {app.candidateName}
                </TableCell>

                <TableCell>

                  <Link
                    href={app.resumeUrl}
                    target="_blank"
                  >
                    View Resume
                  </Link>

                </TableCell>

                <TableCell>
                  {app.status}
                </TableCell>
                <TableCell>

  <Button
    size="small"
    color="primary"
    onClick={() =>
      handleStatus(app.id, "SHORTLISTED")
    }
  >
    Shortlist
  </Button>

  <Button
    size="small"
    color="error"
    onClick={() =>
      handleStatus(app.id, "REJECTED")
    }
  >
    Reject
  </Button>

  <Button
    size="small"
    color="success"
    onClick={() =>
      handleStatus(app.id, "HIRED")
    }
  >
    Hire
  </Button>

</TableCell>

              </TableRow>

            ))}

          </TableBody>

        </Table>

      </Paper>


  );
}