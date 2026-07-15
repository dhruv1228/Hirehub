import api from "../api/axios";
import type { Notification } from "../types/notification";

export async function getNotifications(): Promise<Notification[]> {
  const response = await api.get("/notifications");
  return response.data.data;
}

export async function markAsRead(id: number) {
  await api.patch(`/notifications/${id}/read`);
}

export async function getUnreadCount(): Promise<number> {
  const response = await api.get("/notifications/unread-count");
  return response.data.data;
}