package com.example.patterns.structural.facade.v1;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.io.File;

@Log4j2
public class Facade {
    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtube_video.ogg", "mp4");
        log.info(mp4Video);
    }
}

@Log4j2
class VideoConversionFacade {
    public File convertVideo(String fileName, String destinationFormat) {
        VideoFile file = new VideoFile(fileName);
        String sourceFormat = file.getCodecType();
        Codec sourceCodec = CodecFactory.create(sourceFormat);
        Codec destinationCodec = CodecFactory.create(destinationFormat);

        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        log.info("BitrateReader: reading... \nfile {}\nread: {}", file, buffer);

        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        log.info("BitrateReader: writing... \nfile {}\nconvert: {}", file, intermediateResult);

        final File result = (new AudioMixer()).fix(intermediateResult);
        log.info("AudioMixer: fixing audio...\nVideoFile: {}\nResult: {}", intermediateResult.getFullName(), result);
        return result;
    }
}

@Data
class VideoFile {
    private String name;
    private String codecType;

    public VideoFile(String name) {
        final String[] split = name.split("\\.");
        this.name = split[0];
        this.codecType = split[1];
    }

    public String getFullName(){
        return name + "." + codecType;
    }
}

interface Codec {
    default VideoFile read(VideoFile file) {
        return file;
    }
    VideoFile convert(VideoFile file);
}

@ToString
class MPEG4CompressionCodec implements Codec {
    public String type = "mp4";

    @Override
    public VideoFile convert(VideoFile file) {
        file.setCodecType(type);
        return file;
    }
}

@ToString
class OggCompressionCodec implements Codec {
    public String type = "ogg";

    @Override
    public VideoFile convert(VideoFile file) {
        file.setCodecType(type);
        return file;
    }
}

class CodecFactory {
    public static Codec create(String type) {
        if (type.equals("mp4")) {
            return new MPEG4CompressionCodec();
        } else {
            return new OggCompressionCodec();
        }
    }
}

@Log4j2
class BitrateReader {
    public static VideoFile read(VideoFile file, Codec codec) {
        return codec.read(file);
    }

    public static VideoFile convert(VideoFile file, Codec codec) {
        return codec.convert(file);
    }
}

@Log4j2
class AudioMixer {
    public File fix(VideoFile result) {
        return new File("tmp/" + result.getFullName());
    }
}