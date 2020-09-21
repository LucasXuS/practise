package mindview.thinkinginjava.chapter21.case31;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class GreenhouseSchedulerDemo {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String value) {
        this.thermostat = value;
    }

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
            new ScheduledThreadPoolExecutor(10);

    public void schedule(Runnable event, long delay) {
        scheduledThreadPoolExecutor.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initDelay, long period) {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(event, initDelay, period, TimeUnit.MILLISECONDS);
    }

    class LightOn implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning on lights");
            light = true;
        }
    }

    class LightOff implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning off lights");
            light = false;
        }
    }

    class WaterOn implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning on greenhouse water");
            water = true;
        }
    }

    class WaterOff implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning off greenhouse water");
            water = false;
        }
    }

    class ThermostatNight implements Runnable {

        @Override
        public void run() {
            System.out.println("Thermostat to Night setting");
            setThermostat("Night");
        }
    }

    class ThermostatDay implements Runnable {

        @Override
        public void run() {
            System.out.println("Thermostat to Day setting");
            setThermostat("Day");
        }
    }

    class Bell implements Runnable {

        @Override
        public void run() {
            System.out.println("Bing!");
        }
    }

    private Calendar lastTime = Calendar.getInstance();

    {
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 00);
    }

    private float lastTemp = 65.0f;
    private float lastHumidity = 50.0f;
    private int tempDirection = 1;
    private int humidityDirection = 1;

    private Random random = new Random(47);

    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;

        public DataPoint(Calendar d, float temp, float hum) {
            time = d;
            temperature = temp;
            humidity = hum;
        }

        @Override
        public String toString() {
            return time.getTime() + String.format(" temperature: %1$.1f humidity: %2$.2f", temperature, humidity);
        }
    }

    private List<DataPoint> data = Collections.synchronizedList(new ArrayList<>());

    class Terminate implements Runnable {

        @Override
        public void run() {
            System.out.println("Terminating");
            scheduledThreadPoolExecutor.shutdownNow();
            new Thread() {
                @Override
                public void run() {
                    for (DataPoint d : data) {
                        System.out.println(d);
                    }
                }
            }.start();
        }
    }

    class CollectData implements Runnable {

        @Override
        public void run() {
            System.out.println("Collecting data");
            synchronized (GreenhouseSchedulerDemo.this) {
                lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
                if (random.nextInt(2) == 1) {
                    tempDirection = (-1) * tempDirection;
                }
                if (random.nextInt(2) == 1) {
                    humidityDirection = (-1) * humidityDirection;
                }
                lastTemp += tempDirection * (1.0f + random.nextFloat());
                lastHumidity += humidityDirection * random.nextFloat();
                data.add(new DataPoint((Calendar) lastTime.clone(), lastTemp, lastHumidity));
            }
        }
    }

    public static void main(String[] args) {
        GreenhouseSchedulerDemo greenHouse = new GreenhouseSchedulerDemo();
        greenHouse.schedule(greenHouse.new Terminate(), 5000);
        greenHouse.repeat(greenHouse.new ThermostatNight(), 0, 2000);
        greenHouse.repeat(greenHouse.new LightOn(), 0, 200);
        greenHouse.repeat(greenHouse.new LightOff(), 0, 400);
        greenHouse.repeat(greenHouse.new WaterOn(), 0, 600);
        greenHouse.repeat(greenHouse.new WaterOff(), 0, 800);
        greenHouse.repeat(greenHouse.new ThermostatDay(), 0, 1400);
        greenHouse.repeat(greenHouse.new CollectData(), 500, 500);
    }
}
