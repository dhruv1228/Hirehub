import api from "../api/axios";
import type { CandidateDashboard } from "../types/dashboard";
import type { HomeStats } from "../types/homeStats";

export async function getCandidateDashboard(): Promise<CandidateDashboard> {
  const response = await api.get("/dashboard/candidate");
  return response.data.data;
}

export async function getHomeStats(): Promise<HomeStats> {
  const response = await api.get("/dashboard/home-stats");
  return response.data.data;
}