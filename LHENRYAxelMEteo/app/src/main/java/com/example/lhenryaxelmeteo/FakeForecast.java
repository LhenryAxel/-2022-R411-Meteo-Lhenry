package com.example.lhenryaxelmeteo;

/**
 * Fake Forecast
 */
public class FakeForecast implements IForecastProvider{

    /**
     * Give a weather forecast to do test
     * @param location the location
     * @return a weatherForecast
     */
    @Override
    public WeatherForecast getForecast(Location location) {
        WeatherForecast w = new WeatherForecast();
        Weather w1 = new Weather();
        w1.setDay(0);
        w1.setHour(18);
        w1.setTemperature(28);
        w1.setHumidity(45);
        w1.setWindSpeed(1);
        w1.setWindDirection("SW");
        w1.setPrecipitation(3);
        w1.setWeatherCode(WeatherCodes.SMALL_RAIN);

        Weather w2 = new Weather();
        w2.setDay(0);
        w2.setHour(22);
        w2.setTemperature(10);
        w2.setHumidity(20);
        w2.setWindSpeed(40);
        w2.setWindDirection("W");
        w2.setPrecipitation(0);
        w2.setWeatherCode(WeatherCodes.FOGGY_CLOUDED);

        Weather w3 = new Weather();
        w3.setDay(1);
        w3.setHour(0);
        w3.setTemperature(40);
        w3.setHumidity(0);
        w3.setWindSpeed(0);
        w3.setWindDirection("N");
        w3.setPrecipitation(3);
        w3.setWeatherCode(WeatherCodes.CLEAR_SKY);

        Weather w4 = new Weather();
        w4.setDay(1);
        w4.setHour(0);
        w4.setTemperature(-17);
        w4.setHumidity(10);
        w4.setWindSpeed(120);
        w4.setWindDirection("S");
        w4.setPrecipitation(70);
        w4.setWeatherCode(WeatherCodes.SNOW);


        w.addForecast(w1);
        w.addForecast(w2);
        w.addForecast(w3);
        w.addForecast(w4);

        return w;
    }
}
