import { useEffect, useState } from "react";

import {
  getNotifications,
  markAsRead,
} from "../../services/notificationService";
import type { Notification } from "../../types/notification";

import {
  Paper,
  Typography,
  List,
  ListItem,
  ListItemText,
} from "@mui/material";

export default function NotificationsPage() {

  const [notifications, setNotifications] = useState<Notification[]>([]);

  useEffect(() => {
    loadNotifications();
  }, []);

  async function loadNotifications() {
    const data = await getNotifications();
    setNotifications(data);
  }

  async function handleRead(id: number) {
    await markAsRead(id);
    loadNotifications();
  }

  return (


      <Paper sx={{ p: 3 }}>

        <Typography variant="h4" gutterBottom>
          Notifications
        </Typography>

        <List>

          {notifications.map((notification) => (

            <ListItem
              key={notification.id}
              onClick={() => handleRead(notification.id)}
              sx={{
                cursor: "pointer",
                bgcolor: notification.isRead
                  ? "transparent"
                  : "#f5f5f5",
                mb: 1,
                borderRadius: 2,
              }}
            >
              <ListItemText
                primary={notification.title}
                secondary={notification.message}
              />
            </ListItem>

          ))}

        </List>

      </Paper>


  );
}