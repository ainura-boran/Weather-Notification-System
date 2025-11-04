package app;

import data.WeatherStation;

public class Main {
    public static void main(String[] args) {
        WeatherController controller = new WeatherController();
        WeatherStation station = controller.buildDefaultStation();
        controller.demoRun(station);
    }
}