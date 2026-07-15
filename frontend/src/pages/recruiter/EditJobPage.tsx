
import {
  Paper,
  Typography,
  TextField,
  Button,
  MenuItem,
} from "@mui/material";
import { useState } from "react";

import { useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

import {
  getJob,
  updateJob,
} from "../../services/jobService";

import { createJob } from "../../services/jobService";

export default function CreateJobPage() {
    const { id } = useParams();


    const [job, setJob] = useState({
    title: "",
    description: "",
    location: "",
    salary: "",
    experience: "",
    jobType: "FULL_TIME",
    workMode: "ONSITE",
    companyId: "",
    });
            useEffect(() => {
        loadJob();
        }, []);

        async function loadJob() {

        const data = await getJob(Number(id));

        setJob({
            title: data.title,
            description: data.description,
            location: data.location,
            salary: data.salary,
            experience: data.experience,
            jobType: data.jobType,
            workMode: data.workMode,
            companyId: data.companyId,
        });
        }
    const navigate = useNavigate();
    async function handleSubmit() {

        try {

            await updateJob(Number(id), {
            ...job,
            salary: Number(job.salary),
            companyId: Number(job.companyId),
            });

            alert("Job updated successfully");

            navigate("/recruiter/jobs");

        } catch (error) {

            console.error(error);

            alert("Update failed");
        }
        }
  return (

      <Paper sx={{ p: 3, maxWidth: 700 }}>
        <Typography variant="h4" gutterBottom>
          Edit Job
        </Typography>

        <TextField
            label="Job Title"
            fullWidth
            margin="normal"
            value={job.title}
            onChange={(e) =>
                setJob({ ...job, title: e.target.value })
            }
            />

        <TextField
            label="Description"
            fullWidth
            multiline
            rows={4}
            margin="normal"
            value={job.description}
            onChange={(e) =>
                setJob({ ...job, description: e.target.value })
            }
            />

        <TextField
            label="Location"
            fullWidth
            margin="normal"
            value={job.location}
            onChange={(e) =>
                setJob({ ...job, location: e.target.value })
            }
            />
            <TextField
            label="Salary"
            type="number"
            fullWidth
            margin="normal"
            value={job.salary}
            onChange={(e) =>
                setJob({ ...job, salary: e.target.value })
            }
            />

            <TextField
            label="Experience"
            fullWidth
            margin="normal"
            placeholder="2-4 Years"
            value={job.experience}
            onChange={(e) =>
                setJob({ ...job, experience: e.target.value })
            }
            />
            <TextField
            select
            label="Job Type"
            fullWidth
            margin="normal"
            value={job.jobType}
            onChange={(e) =>
                setJob({ ...job, jobType: e.target.value })
            }
            >
            <MenuItem value="FULL_TIME">Full Time</MenuItem>
            <MenuItem value="PART_TIME">Part Time</MenuItem>
            <MenuItem value="INTERNSHIP">Internship</MenuItem>
            <MenuItem value="CONTRACT">Contract</MenuItem>
            </TextField>

            <TextField
            select
            label="Work Mode"
            fullWidth
            margin="normal"
            value={job.workMode}
            onChange={(e) =>
                setJob({ ...job, workMode: e.target.value })
            }
            >
            <MenuItem value="ONSITE">Onsite</MenuItem>
            <MenuItem value="REMOTE">Remote</MenuItem>
            <MenuItem value="HYBRID">Hybrid</MenuItem>
            </TextField>



       <Button
        variant="contained"
        sx={{ mt: 2 }}
        onClick={handleSubmit}
        >
        Update Job
        </Button>
      </Paper>

  );
}