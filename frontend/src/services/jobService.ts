import api from "../api/axios";
import type { Job } from "../types/job";
import type { PageResponse } from "../types/page";

export async function createJob(job: any) {
  const response = await api.post("/jobs", job);
  return response.data.data;
}

export async function getMyJobs() {
  const response = await api.get("/jobs/my");
  return response.data.data;
}

export async function deleteJob(id: number) {
  await api.delete(`/jobs/${id}`);
}

export async function getJobs(
  page: number = 0,
  size: number = 10
): Promise<PageResponse<Job>> {

  const response = await api.get("/jobs", {
    params: {
      page,
      size,
    },
  });

  return response.data.data;
}

export async function getJob(id: number) {
  const response = await api.get(`/jobs/${id}`);
  return response.data.data;
}

export async function getJobById(id: number): Promise<Job> {
  const response = await api.get(`/jobs/${id}`);
  return response.data.data;
}

export async function updateJob(id: number, job: any) {
  const response = await api.put(`/jobs/${id}`, job);
  return response.data.data;
}

export async function filterJobs(
  title: string,
  location: string,
  jobType: string,
  workMode: string,
  page: number = 0,
  size: number = 10
): Promise<PageResponse<Job>> {

  const response = await api.get("/jobs/filter", {
    params: {
      title: title || undefined,
      location: location || undefined,
      jobType: jobType || undefined,
      workMode: workMode || undefined,
      page,
      size,
    },
  });

  return response.data.data;
}