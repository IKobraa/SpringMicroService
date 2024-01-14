package de.thbingen.UserService.websocket;


import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.UUID;
import java.io.DataOutputStream;
import java.util.Map;


public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        final String randomId = UUID.randomUUID().toString();
        LOG.info("User with ID '{}' opened the page", randomId);

        sendId(randomId);
        System.out.println(randomId);
        return new UserPrincipal(randomId);
    }


        public void sendId(String randomId)  {

            try {

                URL url = new URL("http://localhost:8085/api/user/saveID");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");

                try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                    outputStream.writeBytes(randomId);
                    outputStream.flush();
                }

                int responseCode = connection.getResponseCode();
                System.out.println("Response Code: " + responseCode);

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    System.out.println("Response: " + response.toString());
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
