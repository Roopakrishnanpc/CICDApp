package com.CICD.monitorning;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DummpyHealthCheck implements HealthIndicator {

    @Autowired
    private Environment env;

    @Override
    public Health health() {
        try {
            if (isService()) {
                return Health.up().withDetail("Dummy service", "is working good").build();
            } else {
                return Health.down().withDetail("Dummy service", "is not working good").build();
            }
        } catch (IOException e) {
            return Health.down(e).withDetail("Dummy service", "error occurred: " + e.getMessage()).build();
        }
    }

    private boolean isService() throws IOException {
        String address = env.getProperty("dummyservice.address");
        String port = env.getProperty("dummyservice.port");
        return isAddressReachable(address, Integer.parseInt(port), 3000);
    }

    private boolean isAddressReachable(String address, int port, int timeout) throws IOException {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(address, port), timeout);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
