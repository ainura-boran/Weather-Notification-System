package app;

import data.WeatherInfo;
import data.WeatherStation;
import observers.DisplayBoard;
import observers.EmailNotifier;
import observers.MobileApp;
import strategy.ManualInputStrategy;
import strategy.RealTimePollingStrategy;
import strategy.ScheduledBatchStrategy;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class WeatherController {
    public WeatherStation buildDefaultStation() {
        WeatherStation ws = new WeatherStation(new RealTimePollingStrategy());
        ws.attach(new MobileApp("Ainur"));
        ws.attach(new EmailNotifier("ainur@mail.ru"));
        ws.attach(new DisplayBoard("board-1"));
        return ws;
    }

    public void demoRun(WeatherStation ws) {
        System.out.println("--- Weather Station ---");
        System.out.println("Current strategy: " + ws.currentStrategyName());

        System.out.println("Update x1 (real-time):");
        ws.update();
        ws.update();

        List<WeatherInfo> batch = List.of(
                WeatherInfo.now(-10.6, 80, 1008, 2.1),
                WeatherInfo.now( 30.6, 48,  991, 9.4),
                WeatherInfo.now( 16.9, 72, 1002, 3.3)
        );

        ws.setStrategy(new ScheduledBatchStrategy(batch));
        System.out.println("Update x2 (batch):");
        ws.update();
        ws.update();
        ws.update();

        ws.setStrategy(new ManualInputStrategy(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter tempC, humidity%, pressure hPa, wind m/s: ");
            try {
                double t = sc.nextDouble();
                int    h = sc.nextInt();
                int    p = sc.nextInt();
                double w = sc.nextDouble();
                return new WeatherInfo(LocalDateTime.now(), t, h, p, w);
            } catch (Exception e) {
                System.out.println("Invalid input → using fallback.");
                return null;
            }
        }));

        System.out.println("Update x3 (manual input):");
        ws.update();

        System.out.println("--- completed ---");
    }
}
