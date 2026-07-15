export interface ApplyJobRequest {
  jobId: number;
  resumeUrl: string;
  coverLetter: string;
}

export interface Application {
  id: number;
  candidateId: number;
  candidateName: string;
  jobId: number;
  jobTitle: string;
  companyName: string;
  status: string;
  resumeUrl: string;
  coverLetter: string;
  appliedAt: string;
}