package com.example.patterns.proxy.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;

@Log4j2
public class Proxy {
    public static void main(String[] args) {
        Downloader naiveDownloader = new Downloader(new YouTubeClass());
        Downloader cacheDownloader = new Downloader(new YouTubeCacheProxy());
        long naive = test(naiveDownloader);
        long cache = test(cacheDownloader);
        log.info("Time elapsed: {}ms", naive);
        log.info("Time elapsed: {}ms", cache);
        log.info("Time saved by caching proxy: " + (naive - cache) + "ms");
    }

    private static long test(Downloader downloader) {
        long startTime = System.currentTimeMillis();
        downloader.renderPopularVideos();
        downloader.renderPopularVideos();
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderVideoPage("dancesvideoo");
        downloader.renderVideoPage("dancesvideoo");
        return System.currentTimeMillis() - startTime;
    }
}

@Log4j2
class Downloader {
    private final YouTubeLib api;

    public Downloader(YouTubeLib api) {
        this.api = api;
    }

    public void renderPopularVideos() {
        HashMap<String, Video> list = api.getPopularVideos();
        log.info("--------------Most popular videos on YouTube-----------------");
        for (Video video : list.values()) {
            log.info("ID: {} / Title: {}", video.id, video.title);
        }
        log.info("-------------------------------");
    }

    public void renderVideoPage(String videoId) {
        Video video = api.getVideo(videoId);
        log.info("--------------Video page-----------------");
        log.info("ID: {} / Title: {}", video.id, video.title);
        log.info("-------------------------------");
    }
}

@AllArgsConstructor
@Getter
class Video {
    public String id;
    public String title;
}

interface YouTubeLib {
    HashMap<String, Video> getPopularVideos();
    Video getVideo(String videoId);
}

@Log4j2
class YouTubeClass implements YouTubeLib {
    @Override
    public HashMap<String, Video> getPopularVideos() {
        connectToServer("http://www.youtube.com");
        return getRandomVideos();
    }

    @Override
    public Video getVideo(String videoId) {
        connectToServer("http://www.youtube.com/" + videoId);
        return getSomeVideo(videoId);
    }

    // Fake methods to simulate network activity. They as slow as a real life.
    private void connectToServer(String server) {
        log.info("Connecting to {} ...", server);
        networkLatency();
        log.info("Connected!");
    }

    private HashMap<String, Video> getRandomVideos() {
        networkLatency();
        HashMap<String, Video> hmap = new HashMap<>();
        hmap.put("catzzzzzzzzz", new Video("sadgahasgdas", "Catzzzz.avi"));
        hmap.put("mkafksangasj", new Video("mkafksangasj", "Dog play with ball.mp4"));
        return hmap;
    }

    private Video getSomeVideo(String videoId) {
        networkLatency();
        return new Video(videoId, "Some video title");
    }

    private int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    private void networkLatency() {
        for (int i = 0; i < random(5, 10); i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

@Log4j2
class YouTubeCacheProxy implements YouTubeLib {
    private final YouTubeLib youtubeService;
    private HashMap<String, Video> cachePopular = new HashMap<>();
    private final HashMap<String, Video> cacheAll = new HashMap<>();

    public YouTubeCacheProxy() {
        //инициализируем через объект проксируемого класса
        this.youtubeService = new YouTubeClass();
    }

    @Override
    public HashMap<String, Video> getPopularVideos() {
        if (cachePopular.isEmpty()) {
            warmCachePopular();
        } else {
            log.info("Retrieved list from cache.");
        }
        return cachePopular;
    }

    @Override
    public Video getVideo(String videoId) {
        Video video = getVideoFromCacheAll(videoId);
        if (video == null) {
            //вызов оригинального метода проксируемого класса
            video = getVideoFromNative(videoId);
            setVideoToCacheAll(video);
        } else {
            log.info("Retrieved video '{}' from cache.", videoId);
        }
        return video;
    }

    private Video getVideoFromCacheAll(String videoId) {
        return cacheAll.get(videoId);
    }

    private void setVideoToCacheAll(Video video) {
        cacheAll.put(video.getId(), video);
    }

    private Video getVideoFromNative(String videoId) {
        return youtubeService.getVideo(videoId);
    }

    private void warmCachePopular() {
        cachePopular = youtubeService.getPopularVideos();
    }
}
