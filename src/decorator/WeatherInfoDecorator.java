package decorator;

import data.WeatherInfo;

public interface WeatherInfoDecorator {
    WeatherInfo decorate(WeatherInfo data);
}
