import { Navigate } from "react-router-dom";
import { getToken, getUser } from "../utils/auth";

interface ProtectedRouteProps {
  children: React.ReactNode;
  role?: "CANDIDATE" | "RECRUITER";
}

export default function ProtectedRoute({
  children,
  role,
}: ProtectedRouteProps) {

  const token = getToken();

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  const user = getUser();

  if (role && user?.role !== role) {
    return <Navigate to="/login" replace />;
  }

  return <>{children}</>;
}