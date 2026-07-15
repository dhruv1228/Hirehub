import {
  Drawer,
  List,
  ListItemButton,
  ListItemText,
  ListItemIcon,
  Toolbar,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import PersonIcon from "@mui/icons-material/Person";
import { useEffect, useState } from "react";
import { getMyCompany } from "../../services/companyService";

const drawerWidth = 240;

export default function Sidebar() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user") || "{}");

  const isCandidate = user.role === "CANDIDATE";
  const isRecruiter = user.role === "RECRUITER";

  const [company, setCompany] = useState(null);

  useEffect(() => {
    if (!isRecruiter) return;

    loadCompany();
  }, []);

  async function loadCompany() {

  try {

    const data = await getMyCompany();

    

    setCompany(data);

  } catch (error) {

    console.log("Company Error:", error);

    setCompany(null);

  }

}


  return (
    <Drawer
      variant="permanent"
      sx={{
        width: drawerWidth,
        "& .MuiDrawer-paper": {
          width: drawerWidth,
        },
      }}
    >
      <Toolbar />

      <List>

  {isCandidate && (
    <>
      <ListItemButton onClick={() => navigate("/candidate/dashboard")}>
        <ListItemText primary="Dashboard" />
      </ListItemButton>

      <ListItemButton onClick={() => navigate("/candidate/jobs")}>
        <ListItemText primary="Jobs" />
      </ListItemButton>

      <ListItemButton onClick={() => navigate("/candidate/applications")}>
        <ListItemText primary="Applications" />
      </ListItemButton>

      <ListItemButton onClick={() => navigate("/candidate/profile")}>
        <ListItemIcon>
          <PersonIcon />
        </ListItemIcon>
        <ListItemText primary="Profile" />
      </ListItemButton>

      <ListItemButton
        onClick={() => navigate("/candidate/notifications")}
      >
        <ListItemText primary="Notifications" />
      </ListItemButton>
    </>
  )}

  {isRecruiter && (
    <>
      <ListItemButton onClick={() => navigate("/recruiter/dashboard")}>
        <ListItemText primary="Dashboard" />
      </ListItemButton>

      {company ? (
      <ListItemButton
        onClick={() => navigate("/recruiter/company")}
      >
        <ListItemText primary="Company Profile" />
      </ListItemButton>
    ) : (
      <ListItemButton
        onClick={() => navigate("/recruiter/company/create")}
      >
        <ListItemText primary="Create Company" />
      </ListItemButton>
    )}

      <ListItemButton onClick={() => navigate("/recruiter/jobs")}>
        <ListItemText primary="Jobs" />
      </ListItemButton>

      <ListItemButton
        onClick={() => navigate("/recruiter/jobs/create")}
      >
        <ListItemText primary="Create Job" />
      </ListItemButton>

      <ListItemButton
        onClick={() => navigate("/recruiter/notifications")}
      >
        <ListItemText primary="Notifications" />
      </ListItemButton>
    </>
  )}

  <ListItemButton
    onClick={() => {
      localStorage.clear();
      navigate("/login");
    }}
  >
    <ListItemText primary="Logout" />
  </ListItemButton>

</List>
    </Drawer>
  );
}