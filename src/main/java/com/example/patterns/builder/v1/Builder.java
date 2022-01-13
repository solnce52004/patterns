package com.example.patterns.builder.v1;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Builder {
    public static void main(String[] args) {
        Cluster cluster = Cluster
                .runtimeBuilder()
                .addContactPoints("localhost")
                .withPort(3165)
                .build();

        log.info(cluster);
    }
}

@NoArgsConstructor
@Data
class Cluster {
    private String[] hosts;
    private int port;

    public static RuntimeBuilder runtimeBuilder() {
        return new RuntimeBuilder();
    }

    public static class RuntimeBuilder {
        private final Cluster cluster = new Cluster();

        public RuntimeBuilder addContactPoints(String... hosts) {
            if (hosts == null || hosts.length == 0) {
                throw new IllegalArgumentException("Hosts must be set!");
            }
            cluster.setHosts(hosts);
            return this;
        }

        public RuntimeBuilder withPort(int port) {
            cluster.setPort(port);
            return this;
        }

        public Cluster build() {
            return cluster;
        }
    }
}
