package factory;

import observers.DisplayBoard;
import observers.EmailNotifier;
import observers.MobileApp;
import observers.WeatherObserver;

public class ObserverFactory {
    public static WeatherObserver create(String type, String id) {
        switch (type.toLowerCase()) {
            case "mobile":
                return new MobileApp(id);

            case "email":
                return new EmailNotifier(id);

            case "board":
                return new DisplayBoard(id);

            default:
                throw new IllegalArgumentException("Unknown observer type: " + type);
        }
    }
}

