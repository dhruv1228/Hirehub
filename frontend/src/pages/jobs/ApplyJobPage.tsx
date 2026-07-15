import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  Button,
  Paper,
  TextField,
  Typography,
} from "@mui/material";


import { applyJob } from "../../services/applicationService";
import { uploadResume } from "../../services/resumeService";

export default function ApplyJobPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [resumeFile, setResumeFile] = useState<File | null>(null);
  const [coverLetter, setCoverLetter] = useState("");
  const [uploading, setUploading] = useState(false);

  async function handleSubmit() {
    if (!resumeFile) {
      alert("Please select a resume.");
      return;
    }

    try {
      setUploading(true);

      const uploadedResume = await uploadResume(resumeFile);

      await applyJob({
        jobId: Number(id),
        resumeUrl: uploadedResume.resumeUrl,
        coverLetter,
      });

      alert("Application submitted successfully!");
      navigate("/candidate/dashboard");
    } catch (error) {
      console.error(error);
      alert("Failed to submit application.");
    } finally {
      setUploading(false);
    }
  }

  return (

      <Paper sx={{ p: 4, maxWidth: 700 }}>
        <Typography variant="h4" gutterBottom>
          Apply for Job
        </Typography>

        <Button
          component="label"
          variant="outlined"
        >
          Choose Resume

          <input
            hidden
            type="file"
            accept=".pdf,.doc,.docx"
            onChange={(e) =>
              setResumeFile(e.target.files?.[0] ?? null)
            }
          />
        </Button>

        {resumeFile && (
          <Typography sx={{ mt: 1 }}>
            Selected: {resumeFile.name}
          </Typography>
        )}

        <TextField
          label="Cover Letter"
          fullWidth
          multiline
          rows={6}
          margin="normal"
          value={coverLetter}
          onChange={(e) => setCoverLetter(e.target.value)}
        />

        <Button
          variant="contained"
          sx={{ mt: 3 }}
          onClick={handleSubmit}
          disabled={uploading}
        >
          {uploading ? "Submitting..." : "Submit Application"}
        </Button>
      </Paper>

  );
}