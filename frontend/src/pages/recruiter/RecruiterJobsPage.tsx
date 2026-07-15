import { useEffect, useState } from "react";
import DashboardLayout from "../../layouts/DashboardLayout";
import { getMyJobs } from "../../services/jobService";
import { deleteJob } from "../../services/jobService";

import {
  Paper,
  Typography,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  IconButton,
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import { Link } from "react-router-dom";
import GroupsIcon from "@mui/icons-material/Groups";

import DeleteIcon from "@mui/icons-material/Delete";

export default function RecruiterJobsPage() {

  const [jobs, setJobs] = useState<any[]>([]);

  useEffect(() => {
    loadJobs();
  }, []);

  async function loadJobs() {
    const data = await getMyJobs();
    setJobs(data);
  }
  async function handleDelete(id: number) {

        if (!window.confirm("Delete this job?")) {
            return;
        }

        try {
            await deleteJob(id);
            loadJobs();
        } catch (error) {
            console.error(error);
        }
        }

  return (


      <Paper sx={{ p: 3 }}>

        <Typography variant="h4" gutterBottom>
          Manage Jobs
        </Typography>

        <Table>

          <TableHead>
            <TableRow>
                <TableCell>Title</TableCell>
                <TableCell>Location</TableCell>
                <TableCell>Type</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Actions</TableCell>
            </TableRow>
            </TableHead>

          <TableBody>

            {jobs.map(job => (

              <TableRow key={job.id}>
                <TableCell>{job.title}</TableCell>
                <TableCell>{job.location}</TableCell>
                <TableCell>{job.jobType}</TableCell>
                <TableCell>{job.status}</TableCell>
                <TableCell>
                <IconButton
                    color="error"
                    onClick={() => handleDelete(job.id)}
                >
                    <DeleteIcon />
                </IconButton>
                <IconButton
                    color="primary"
                    component={Link}
                    to={`/recruiter/jobs/${job.id}/edit`}
                    >
                    <EditIcon />
                </IconButton>
                <IconButton
                    color="secondary"
                    component={Link}
                    to={`/recruiter/jobs/${job.id}/applications`}
                >
                    <GroupsIcon />
                </IconButton>
                </TableCell>
              </TableRow>

            ))}

          </TableBody>

        </Table>

      </Paper>


  );
}