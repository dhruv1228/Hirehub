import {
  Box,
  Button,
  Container,
  Paper,
  TextField,
  Typography,
} from "@mui/material";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import {
  loginSchema,
  type LoginFormData,
} from "../../validation/loginSchema";
import { login } from "../../services/authService";
import { useNavigate } from "react-router-dom";
import { getMyCompany } from "../../services/companyService";

export default function LoginPage() {
    const navigate = useNavigate();
    const {
        register,
        handleSubmit,
        formState: { errors },
        } = useForm<LoginFormData>({
        resolver: zodResolver(loginSchema),
    });
    const onSubmit = async (data: LoginFormData) => {
    try {
        const response = await login(data);

        console.log(response);

        localStorage.setItem("token", response.accessToken);
        localStorage.setItem("user", JSON.stringify(response.user));

        if (response.user.role === "CANDIDATE") {
    navigate("/candidate/dashboard");
      } else {
          try {
              await getMyCompany();

              navigate("/recruiter/dashboard");
          } catch {
              navigate("/recruiter/company/create");
          }
      }

    } catch (error) {
        console.error(error);
        alert("Invalid email or password");
    }
};

  return (
    <Container maxWidth="sm">
      <Box
        component="form"
        onSubmit={handleSubmit(onSubmit)}
        sx={{
            display: "flex",
            flexDirection: "column",
        }}>
        <Paper elevation={4} sx={{ p: 4, width: "100%" }}>
          <Typography
            variant="h4"
            align="center"
            gutterBottom
          >
            HireHub Login
          </Typography>
          <Typography
          align="center"
          sx={{ mt: 2 }}
        >
          Don't have an account?{" "}
          <Button onClick={() => navigate("/register")}>
            Register
          </Button>
        </Typography>

          <TextField
            label="Email"
            fullWidth
            margin="normal"
            {...register("email")}
            error={!!errors.email}
            helperText={errors.email?.message}
        />

          <TextField
            label="Password"
            type="password"
            fullWidth
            margin="normal"
            {...register("password")}
            error={!!errors.password}
            helperText={errors.password?.message}
        />

          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{ mt: 3 }}
        >
            Login
        </Button>
        </Paper>
      </Box>
    </Container>
  );
}