package com.example.patterns.builder;

import lombok.*;
import lombok.Builder;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Builder2 {
    public static void main(String[] args) {
        Cluster1 cluster10 = Cluster1
                .cluster1Builder()
                .setHost("0.0.0.0")
                .setPort(3165)
                .build();

        Cluster2 cluster20 = Cluster2
                .cluster2Builder()
                .setHost("127.0.0.1")
                .setPort(3306)
                .build();

        Cluster3 cluster30 = new Cluster3
                .Cluster3Builder()
                .host("192.67.0.20")
                .port(27017)
                .isActive(true)
                .build();

        log.info(cluster10);
        log.info(cluster20);
        log.info(cluster30);
    }
}

interface MainBuilder {
    MainBuilder setHost(String hosts);

    MainBuilder setPort(int port);
}

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
class Cluster1 {
    private String host;
    private int port;
    private boolean isActive;

    public static Cluster1Builder cluster1Builder() {
        return new Cluster1Builder();
    }

    public static class Cluster1Builder implements MainBuilder{
        final Cluster1 cluster1 = new Cluster1();

        public Cluster1Builder setHost(String host) {
            cluster1.setHost(host);
            return this;
        }

        public Cluster1Builder setPort(int port) {
            cluster1.setPort(port);
            return this;
        }

        public Cluster1 build() {
            return cluster1;
        }
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class Cluster2 {
    private String host;
    private int port;
    private boolean isActive;

    public static Cluster2Builder cluster2Builder() {
        return new Cluster2Builder();
    }
}

@Data
@Accessors(chain = true)
class Cluster2Builder implements MainBuilder {
    private String host;
    private int port;
    private boolean isActive;

    public Cluster2 build() {
        final Cluster2 cluster2 = new Cluster2();
        cluster2.setHost(host);
        cluster2.setPort(port);
        cluster2.setActive(true);
        return cluster2;
    }
}

@Builder
@Data
class Cluster3 {
    private final String host;
    private final int port;
    private boolean isActive;
}