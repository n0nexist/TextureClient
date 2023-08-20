package client.texture.textureclient.utils;

public class TimeHelper {
    private long lastMS = 0L;

    public int convertToMS(int d) {
        return 1000 / d;
    }

    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public boolean hasReacher(long miliseconds) {
        return (getCurrentMS() - this.lastMS >= miliseconds);
    }

    public boolean hasTimeReached(double delay) {
        return ((System.currentTimeMillis() - this.lastMS) <= delay);
    }

    public long getDelay() {
        return System.currentTimeMillis() - this.lastMS;
    }

    public void reset() {
        this.lastMS = System.currentTimeMillis();
    }

    public void setLastMS() {
        this.lastMS = System.currentTimeMillis();
    }

    public void setLastMS(long lastMS) {
        this.lastMS = lastMS;
    }

    public boolean hasTimePassed(long delay) {
        return (System.currentTimeMillis() >= this.lastMS + delay);
    }

    public boolean hasTimePassed(double delay) {
        return (System.currentTimeMillis() >= this.lastMS + delay);
    }

    public long getLastMS() {
        return this.lastMS;
    }
}