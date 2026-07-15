import api from "../api/axios";

import type {CompanyFormData} from "../validation/companySchema";

export interface CreateCompanyRequest {
  name: string;
  description: string;
  industry: string;
  website: string;
  headquarters: string;
  companySize: string;
}

export async function getMyCompany() {
  const response = await api.get("/companies/my-company");
  return response.data.data;
}


export async function createCompany(
  data: CreateCompanyRequest
) {
  const response = await api.post(
    "/companies",
    data
  );

  return response.data.data;
}




export async function updateCompany(
    id: number,
    data: CompanyFormData
) {
    const response = await api.put(
        `/companies/${id}`,
        data
    );

    return response.data.data;
}