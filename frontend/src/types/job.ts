export interface Job {
  id: number;
  title: string;
  description: string;
  location: string;
  salary: number;
  experience: string;
  jobType: string;
  workMode: string;
  status: string;
  companyId: number;
  companyName: string;
  applied: boolean;
}