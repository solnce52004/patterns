package com.example.patterns.creational.prototype.v1;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Prototype {
    public static void main(String[] args) {
        //2021-01-01 22:00:30
        final TrafficStatistic traffic1 = new TrafficStatistic(123456789L, new Date(1641063630L));
        traffic1.setEstimatedTraffic(500L);
        //2021-01-12 22:00:30
        final TrafficStatistic traffic2 = new TrafficStatistic(222222222L, new Date(1642014030L));
        traffic2.setEstimatedTraffic(1500L);

        final List<Statistic<TrafficStatistic>> statisticList = new StatisticProcessor()
                .process(123456789L, Arrays.asList(traffic1, traffic2));

        log.info(statisticList);
    }
}

class StatisticProcessor {
    public List<Statistic<TrafficStatistic>> process(long domainId, List<TrafficStatistic> stats) {
        Date now = new Date();
        //2021-01-01 22:00:30
        final TrafficStatistic traffic = new TrafficStatistic(123456789L, new Date(1609527630L));
        traffic.setEstimatedTraffic(-5000L);

        return stats.stream()
                .filter(stat -> stat.getDomainId() == domainId)
                .map(stat -> stat.clone(traffic))
                .map(stat -> stat.invert(now))
                .collect(Collectors.toList());
    }
}

interface Statistic<S> {
    S clone(S delta);

    S invert(Date forDate);
}

@Data
class TrafficStatistic implements Statistic<TrafficStatistic> {
    private final long domainId;
    private final Date rankDate;
    private long estimatedTraffic;

    public TrafficStatistic(long domainId, Date rankDate) {
        this.domainId = domainId;
        this.rankDate = rankDate;
    }

    private TrafficStatistic(long domainId, Date rankDate, long traffic) {
        this(domainId, rankDate);
        this.estimatedTraffic = traffic;
    }

    @Override
    public TrafficStatistic clone(TrafficStatistic delta) {
        return new TrafficStatistic(domainId, rankDate, (estimatedTraffic + delta.estimatedTraffic));
    }

    @Override
    public TrafficStatistic invert(Date forDate) {
        return new TrafficStatistic(domainId, forDate, -estimatedTraffic);
    }

    @Override
    public String toString() {
        return "TrafficStats{domainId=" + domainId + ", rankDate=" + rankDate +
                ", estimatedTraffic=" + estimatedTraffic + "}";
    }
}
