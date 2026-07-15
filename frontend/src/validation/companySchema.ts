import { z } from "zod";

export const companySchema = z.object({
  name: z.string().min(2, "Company name is required"),
  description: z.string().min(5, "Description is required"),
  industry: z.string().min(2, "Industry is required"),
  website: z.string().url("Enter a valid website"),
  headquarters: z.string().min(2, "Headquarters is required"),
  companySize: z.string().min(1, "Company size is required"),
});

export type CompanyFormData = z.infer<typeof companySchema>;