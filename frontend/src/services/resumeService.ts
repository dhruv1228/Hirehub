import api from "../api/axios";
import type { ResumeResponse } from "../types/resume";

export async function uploadResume(
  file: File
): Promise<ResumeResponse> {

  const formData = new FormData();

  formData.append("file", file);

  const response = await api.post(
    "/resume/upload",
    formData,
    {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }
  );

  return response.data.data;
}