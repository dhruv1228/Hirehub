import {
  AppBar,
  Toolbar,
  Typography,
} from "@mui/material";
import NotificationsIcon from "@mui/icons-material/Notifications";
import Badge from "@mui/material/Badge";
import IconButton from "@mui/material/IconButton";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getUnreadCount } from "../../services/notificationService";
import {
  connectNotificationSocket,
  disconnectNotificationSocket,
} from "../../services/websocketService";

export default function Navbar() {
  const navigate = useNavigate();

const [unreadCount, setUnreadCount] = useState(0);

useEffect(() => {

  loadUnreadCount();

  const user = JSON.parse(
    localStorage.getItem("user") || "{}"
  );

  if (user.id) {

    connectNotificationSocket(
      user.id,
      () => {
        loadUnreadCount();
      }
    );

  }

  return () => {
    disconnectNotificationSocket();
  };

}, []);

async function loadUnreadCount() {
  try {
    const count = await getUnreadCount();
    setUnreadCount(count);
  } catch (error) {
    console.error(error);
  }
}
  return (
  <AppBar position="fixed">
    <Toolbar>

      <Typography
        variant="h6"
        sx={{ flexGrow: 1 }}
      >
        HireHub
      </Typography>

      <IconButton
        color="inherit"
        onClick={() => {
        const user = JSON.parse(
          localStorage.getItem("user") || "{}"
        );

        if (user.role === "RECRUITER") {
          navigate("/recruiter/notifications");
        } else {
          navigate("/candidate/notifications");
        }
      }}
      >
        <Badge
          badgeContent={unreadCount}
          color="error"
        >
          <NotificationsIcon />
        </Badge>
      </IconButton>

    </Toolbar>
  </AppBar>
);
}