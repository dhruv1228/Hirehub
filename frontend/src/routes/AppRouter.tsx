import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Button } from "@mui/material";
import { login } from "../services/authService";
import LoginPage from "../pages/auth/LoginPage";
import ProtectedRoute from "./ProtectedRoute";
import DashboardLayout from "../layouts/DashboardLayout";

import CandidateDashboard from "../pages/candidate/CandidateDashboard";
import RecruiterDashboard from "../pages/recruiter/RecruiterDashboard";
import JobsPage from "../pages/jobs/JobsPage";
import JobDetailsPage from "../pages/jobs/JobDetailsPage";
import ApplyJobPage from "../pages/jobs/ApplyJobPage";
import MyApplicationsPage from "../pages/candidate/MyApplicationsPage";
import RecruiterJobsPage from "../pages/recruiter/RecruiterJobsPage";
import CreateJobPage from "../pages/recruiter/CreateJobPage";
import EditJobPage from "../pages/recruiter/EditJobPage";
import JobApplicationsPage from "../pages/recruiter/JobApplicationsPage";
import NotificationsPage from "../pages/common/NotificationsPage";
import RegisterPage from "../pages/auth/RegisterPage";
import CreateCompanyPage from "../pages/recruiter/CreateCompanyPage";
import EditCompanyPage from "../pages/recruiter/EditCompanyPage";
import LandingPage from "../pages/LandingPage";
import ProfilePage from "../pages/candidate/ProfilePage";
import CompanyProfilePage from "../pages/recruiter/CompanyProfilePage";

function HomePage() {

  async function test() {

    const res = await login({
      email: "candidate@example.com",
      password: "Password@123"
    });

    console.log(res);
  }

  return (
    <Button
      variant="contained"
      onClick={test}
    >
      Test Login
    </Button>
  );
}



export default function AppRouter() {
  return (
  <BrowserRouter>
    <Routes>

      {/* Public Routes */}
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />

      {/* Common Protected Routes */}
      

      <Route
        path="/jobs/:id"
        element={
          <ProtectedRoute>
            <JobDetailsPage />
          </ProtectedRoute>
        }
      />

      <Route
        path="/jobs/:id/apply"
        element={
          <ProtectedRoute role="CANDIDATE">
            <ApplyJobPage />
          </ProtectedRoute>
        }
      />

      {/* Candidate Routes */}
      <Route
      path="/candidate"
      element={
        <ProtectedRoute role="CANDIDATE">
          <DashboardLayout />
        </ProtectedRoute>
      }
    >
      <Route path="dashboard" element={<CandidateDashboard />} />
      <Route path="jobs" element={<JobsPage />} />
      <Route path="jobs/:id" element={<JobDetailsPage />} />
      <Route path="jobs/:id/apply" element={<ApplyJobPage />} />
      <Route path="applications" element={<MyApplicationsPage />} />
      <Route path="profile" element={<ProfilePage />} />
      <Route path="notifications" element={<NotificationsPage />} />
    </Route>

      {/* Recruiter Routes */}
      <Route
        path="/recruiter"
        element={
          <ProtectedRoute role="RECRUITER">
            <DashboardLayout />
          </ProtectedRoute>
        }
      >
        <Route path="dashboard" element={<RecruiterDashboard />} />
        <Route path="jobs" element={<RecruiterJobsPage />} />
        <Route path="jobs/all" element={<JobsPage />} />
        <Route path="jobs/create" element={<CreateJobPage />} />
        <Route path="company" element={<CompanyProfilePage />} />
        <Route path="jobs/:id/edit" element={<EditJobPage />} />
        <Route
          path="jobs/:id/applications"
          element={<JobApplicationsPage />}
        />
        <Route
          path="company/create"
          element={<CreateCompanyPage />}
        />
        <Route
            path="company/edit"
            element={<EditCompanyPage />}
        />
        <Route
          path="notifications"
          element={<NotificationsPage />}
        />
      </Route>

    </Routes>
  </BrowserRouter>
);
}