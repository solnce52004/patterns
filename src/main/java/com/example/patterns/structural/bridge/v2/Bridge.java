package com.example.patterns.structural.bridge.v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Bridge {
    public static void main(String[] args) {
        log.info("*** TV ***");
        Tester.testDevice(new Tv());
        log.info("*** Radio ***");
        Tester.testDevice(new Radio());
    }
}

@Log4j2
class Tester {
    public static void testDevice(Device device) {
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.volumeUp();
        log.info(device);

        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.mute();
        log.info(device);
    }
}

interface Device {
    int getVolume();
    void setVolume(int percent);
}

@Data
class Radio implements Device {
    private int volume = 30;

    @Override
    public void setVolume(int volume) {
        this.volume = (volume > 100)
                ? 100
                : Math.max(volume, 0);
    }
}

@Data
class Tv implements Device {
    private int volume = 60;

    @Override
    public void setVolume(int volume) {
        this.volume = (volume > 200)
                ? 200
                : Math.max(volume, 0);
    }
}

interface Remote {
    void volumeDown();
    void volumeUp();
}

@NoArgsConstructor
@AllArgsConstructor
class BasicRemote implements Remote {
    protected Device device;

    @Override
    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }

    @Override
    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }
}

class AdvancedRemote extends BasicRemote {
    public AdvancedRemote(Device device) {
        super.device = device;
    }

    public void mute() {
        device.setVolume(0);
    }
}
