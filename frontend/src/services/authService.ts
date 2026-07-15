import api from "../api/axios";
import type { LoginRequest, LoginResponse } from "../types/auth";
import type { RegisterFormData } from "../validation/registerSchema";

export const login = async (
  data: LoginRequest
): Promise<LoginResponse> => {

  const response = await api.post(
    "/auth/login",
    data
  );

  return response.data.data;
};

export async function register(data: RegisterFormData) {
  const response = await api.post("/auth/register", data);
  return response.data.data;
}