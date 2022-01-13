package com.example.patterns.builder.modern;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;


@Builder
@Getter
public class LombokCluster {
    @Singular private List<String> hosts;
    private int port;
    private int retryAttempts;
    private boolean metricsEnabled;
}
