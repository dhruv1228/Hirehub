import { Client } from "@stomp/stompjs";

let client: Client | null = null;

export function connectNotificationSocket(
  userId: number,
  onMessage: () => void
) {
  client = new Client({
    brokerURL: "ws://localhost:8080/ws",

    reconnectDelay: 5000,

    onConnect: () => {
      console.log("WebSocket Connected");

      client?.subscribe(
        `/topic/notifications/${userId}`,
        () => {
          onMessage();
        }
      );
    },

    onStompError: (frame) => {
      console.error("STOMP Error:", frame);
    },

    onWebSocketError: (event) => {
      console.error("WebSocket Error:", event);
    },
  });

  client.activate();
}

export function disconnectNotificationSocket() {
  client?.deactivate();
}