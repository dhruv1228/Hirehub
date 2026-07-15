import api from "../api/axios";
import type { CandidateProfile } from "../types/profile";

export async function getProfile(): Promise<CandidateProfile> {
  const response = await api.get("/profile");
  return response.data.data;
}

export async function updateProfile(
  data: CandidateProfile
): Promise<CandidateProfile> {
  const response = await api.put("/profile", data);
  return response.data.data;
}