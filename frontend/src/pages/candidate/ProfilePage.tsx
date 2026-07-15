import {
  Box,
  Button,
  Paper,
  TextField,
  Typography,
} from "@mui/material";
import { useEffect, useState } from "react";
import type { CandidateProfile } from "../../types/profile";
import {
  getProfile,
  updateProfile,
} from "../../services/profileService";

export default function ProfilePage() {

  const [profile, setProfile] =
    useState<CandidateProfile | null>(null);

  useEffect(() => {
    loadProfile();
  }, []);

  async function loadProfile() {
    try {
      const data = await getProfile();
      setProfile(data);
    } catch (error) {
      console.error(error);
    }
  }

  async function saveProfile() {
    if (!profile) return;

    try {
      await updateProfile(profile);
      alert("Profile updated successfully");
    } catch (error) {
      console.error(error);
    }
  }

  if (!profile) return null;

  return (
    <Paper sx={{ p: 4 }}>

      <Typography
        variant="h4"
        sx={{ mb: 3 }}
      >
        Candidate Profile
      </Typography>

      <TextField
        fullWidth
        label="Bio"
        margin="normal"
        multiline
        rows={4}
        value={profile.bio ?? ""}
        onChange={(e) =>
          setProfile({
            ...profile,
            bio: e.target.value,
          })
        }
      />

      <TextField
        fullWidth
        label="Skills"
        margin="normal"
        value={profile.skills ?? ""}
        onChange={(e) =>
          setProfile({
            ...profile,
            skills: e.target.value,
          })
        }
      />

      <TextField
        fullWidth
        label="Education"
        margin="normal"
        value={profile.education ?? ""}
        onChange={(e) =>
          setProfile({
            ...profile,
            education: e.target.value,
          })
        }
      />

      <TextField
        fullWidth
        label="Experience"
        margin="normal"
        value={profile.experience ?? ""}
        onChange={(e) =>
          setProfile({
            ...profile,
            experience: e.target.value,
          })
        }
      />

      <TextField
        fullWidth
        label="LinkedIn"
        margin="normal"
        value={profile.linkedin ?? ""}
        onChange={(e) =>
          setProfile({
            ...profile,
            linkedin: e.target.value,
          })
        }
      />

      <TextField
        fullWidth
        label="GitHub"
        margin="normal"
        value={profile.github ?? ""}
        onChange={(e) =>
          setProfile({
            ...profile,
            github: e.target.value,
          })
        }
      />

      <TextField
        fullWidth
        label="Portfolio"
        margin="normal"
        value={profile.portfolio ?? ""}
        onChange={(e) =>
          setProfile({
            ...profile,
            portfolio: e.target.value,
          })
        }
      />

      <TextField
        fullWidth
        label="Location"
        margin="normal"
        value={profile.location ?? ""}
        onChange={(e) =>
          setProfile({
            ...profile,
            location: e.target.value,
          })
        }
      />

      <Box sx={{ mt: 3 }}>

        <Button
          variant="contained"
          onClick={saveProfile}
        >
          Save Profile
        </Button>

      </Box>

    </Paper>
  );
}