import api from "../api/axios";
import type {
  Application,
  ApplyJobRequest,
} from "../types/application";

export async function getMyApplications(): Promise<Application[]> {
  const response = await api.get("/applications/my");
  return response.data.data;
}

export async function applyJob(
  request: ApplyJobRequest
): Promise<Application> {
  const response = await api.post(
    "/applications",
    request
  );

  return response.data.data;
}

export async function getApplicationsByJob(jobId: number) {
    const response = await api.get(`/applications/job/${jobId}`);
    return response.data.data;
}

export async function updateApplicationStatus(
  applicationId: number,
  status: string
) {
  const response = await api.patch(
    `/applications/${applicationId}/status`,
    {
      status,
    }
  );

  return response.data.data;
}