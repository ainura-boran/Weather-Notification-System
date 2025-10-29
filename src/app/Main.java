import data.WeatherData;
import observers.EmailNotifier;
import observers.MobileApp;
import observers.WallDisplay;
import strategy.ManualInputStrategy;
import strategy.RealTimePollingStrategy;
import strategy.ScheduledBatchStrategy;

public class Main {
    public static void main(String[] args) {
        WallDisplay wall = new WallDisplay("Expo-2017");
        MobileApp mobile = new MobileApp("Dinara");
        EmailNotifier email = new EmailNotifier("alerts@company.com");

        RealTimePollingStrategy realtime = new RealTimePollingStrategy();
        ScheduledBatchStrategy batch = new ScheduledBatchStrategy(3);
        ManualInputStrategy manual = new ManualInputStrategy();

        WeatherStation station = new WeatherStation(realtime);
        station.register(wall);
        station.register(mobile);
        station.register(email);

        System.out.println("=== Real-Time Polling ===");
        station.updateNow();
        sleep(500);
        station.updateNow();

        station.setStrategy(batch);
        System.out.println("=== Scheduled Batch (3) ===");
        station.updateNow();

        station.setStrategy(manual);
        System.out.println("=== Manual Input ===");
        manual.offer(WeatherData.now(-10.6, 80, 1008, 2.1));
        manual.offer(WeatherData.now(30.6, 48, 991, 9.4));
        station.updateNow();

        manual.offer(WeatherData.now(18.2, 60, 1005, 4.0));
        sleep(400);
        station.updateNow();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}