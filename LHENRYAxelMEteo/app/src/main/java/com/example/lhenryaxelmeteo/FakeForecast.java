package com.example.lhenryaxelmeteo;

public class FakeForecast implements IForecastProvider{
    @Override
    public WeatherForecast getForecast(Location location) {
        WeatherForecast w = new WeatherForecast();
        Weather w1 = new Weather();
        w1.setDay(0);
        w1.setHour(22);
        w1.setTemperature(17);
        w1.setHumidity(30);
        w1.setWindSpeed(10);
        w1.setWindDirection("SW");
        w1.setPrecipitation(3);
        w1.setWeatherCode(WeatherCodes.SMALL_RAIN);

        Weather w2 = new Weather();
        w2.setDay(0);
        w2.setHour(23);
        w2.setTemperature(15);
        w2.setHumidity(20);
        w2.setWindSpeed(40);
        w2.setWindDirection("W");
        w2.setPrecipitation(0);
        w1.setWeatherCode(WeatherCodes.FOGGY_CLOUDED);

        Weather w3 = new Weather();
        w3.setDay(1);
        w3.setHour(0);
        w3.setTemperature(30);
        w3.setHumidity(0);
        w3.setWindSpeed(0);
        w3.setWindDirection("N");
        w3.setPrecipitation(3);
        w3.setWeatherCode(WeatherCodes.CLEAR_SKY);

        Weather w4 = new Weather();
        w4.setDay(1);
        w4.setHour(0);
        w4.setTemperature(-17);
        w4.setHumidity(30);
        w4.setWindSpeed(120);
        w4.setWindDirection("S");
        w4.setPrecipitation(80);
        w4.setWeatherCode(WeatherCodes.SNOW);


        w.addForecast(w1);
        w.addForecast(w2);
        w.addForecast(w3);
        w.addForecast(w4);

        return w;
    }
}
