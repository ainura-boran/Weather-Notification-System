package decorator;

import data.WeatherInfo;

public class ComfortDecorator implements WeatherInfoDecorator {
    @Override
    public WeatherInfo decorate(WeatherInfo data) {
        String comfort;
        if (data.getTemperatureC() < 0) comfort = "❄️ Cold";
        else if (data.getTemperatureC() < 15) comfort = "🌤 Cool";
        else if (data.getTemperatureC() < 25) comfort = "☀️ Warm";
        else comfort = "🔥 Hot";
        System.out.printf("Comfort level: %s%n", comfort);
        return data;
    }
}

