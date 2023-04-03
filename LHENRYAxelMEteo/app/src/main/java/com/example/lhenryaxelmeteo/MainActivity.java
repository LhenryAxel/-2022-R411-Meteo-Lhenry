package com.example.lhenryaxelmeteo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * MainWindow
 */
public class MainActivity extends AppCompatActivity implements LocationListener {

    private ImageButton btnSearch;
    private ImageButton btnPos;
    private EditText editVille;
    private TextView txtCoords1;
    private TextView txtCoords2;
    private TextView txtDate;
    private ImageButton btnPrevious;
    private ImageButton btnNext;
    private ImageView imgWeather;

    private TextView txtTemperature;
    private TextView txtHumidity;
    private TextView txtWindSpeed;
    private TextView txtWindDirection;
    private TextView txtPrecipitation;

    private int changeDay;
    private int changeHour;

    private FakeForecast forecast;
    private WeatherForecast listForecast;

    private Location currentLocation;



    /**
     * Constructor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch = findViewById(R.id.btnSearch);
        btnPos = findViewById(R.id.btnPos);
        editVille = findViewById(R.id.editTown);
        txtCoords1 = findViewById(R.id.txtCoords1);
        txtCoords2 = findViewById(R.id.txtCoords2);
        txtDate = findViewById(R.id.txtDate);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        imgWeather = findViewById(R.id.imgWeather);

        txtTemperature = findViewById(R.id.txtTemperature);
        txtHumidity = findViewById(R.id.txtHumidity);
        txtWindSpeed = findViewById(R.id.txtWindSpeed);
        txtWindDirection = findViewById(R.id.txtWindDirection);
        txtPrecipitation = findViewById(R.id.txtPrecipitation);


        btnSearch.setOnClickListener(this::OnClickSearch);
        btnPos.setOnClickListener(this::OnClickPosition);
        btnNext.setOnClickListener(this::OnClickNext);
        btnPrevious.setOnClickListener(this::OnClickPrevious);

        forecast = new FakeForecast();

        currentLocation = new Location();
    }

    /**
     * On click determinate the position
     * @param view
     */
    private void OnClickPosition(View view) {
        int ret = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        if( ret == PackageManager.PERMISSION_GRANTED) {
            initLocation();
        }
        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},100);
        }
    }

    /**
     * On click search the forecast
     * @param view
     */
    private void OnClickSearch(View view) {
        currentLocation.setCity(editVille.toString());
        currentLocation.setLatitude((float) 47.311);
        currentLocation.setLongitude((float) 5.069);
        ShowLocation(currentLocation);
        listForecast = forecast.getForecast(currentLocation);
        changeDay = 0;
        ShowWeather(listForecast.getForecast(changeDay));
    }

    /**
     * Show the weather given in parameters
     * @param weather given weather
     */
    private void ShowWeather(Weather weather){
        String s = weather.getWindDirection();
        txtTemperature.setText("Température : " + String.valueOf(weather.getTemperature()));
        txtHumidity.setText("Humidité : " + String.valueOf(weather.getHumidity()));
        txtWindDirection.setText("Vitesse du vent : " + weather.getWindSpeed());
        txtWindSpeed.setText("Direction du vent : " + String.valueOf(weather.getWindDirection()));
        txtPrecipitation.setText("Précipitations : " + String.valueOf(weather.getPrecipitation()));
        txtDate.setText(realDay(weather.getDay(), weather.getHour()));

        ChangeWeatherImage(weather.getWeatherCode());

    }

    /**
     * On click go back on previous weather
     * @param view
     */
    private void OnClickPrevious(View view) {
        if (listForecast != null){
            if (changeDay > 0){
                changeDay -= 1;
            }
            ShowWeather(listForecast.getForecast(changeDay));
        }
    }

    /**
     * On click go on next weather
     * @param view
     */
    private void OnClickNext(View view) {
        if (listForecast != null){
            if (changeDay < listForecast.getSize()-1){
                changeDay += 1;
            }
            ShowWeather(listForecast.getForecast(changeDay));
        }
    }



    /**
     * Change the picture based on the weather given
     * @param code code of weather
     */
    private void ChangeWeatherImage(WeatherCodes code){
        switch(code){
            case CLEAR_SKY: imgWeather.setImageResource(getResources().getIdentifier("sunny","drawable",getPackageName())); break;
            case SNOW : imgWeather.setImageResource(getResources().getIdentifier("snow","drawable",getPackageName())); break;
            case HEAVY_RAIN: imgWeather.setImageResource(getResources().getIdentifier("rain","drawable",getPackageName())); break;
            case SMALL_RAIN: imgWeather.setImageResource(getResources().getIdentifier("small_rain","drawable",getPackageName())); break;
            case THUNDERSTORM: imgWeather.setImageResource(getResources().getIdentifier("thunder","drawable",getPackageName())); break;
            case FOGGY_CLOUDED: imgWeather.setImageResource(getResources().getIdentifier("cloudy","drawable",getPackageName())); break;
            case PARTIAL_CLOUDED: imgWeather.setImageResource(getResources().getIdentifier("partial_clouded","drawable",getPackageName())); break;
        }
    }

    /**
     * Show the localisation
     * @param location
     */
    private void ShowLocation(Location location){
        GeoLocFormat format = new GeoLocFormat();
        txtCoords1.setText(GeoLocFormat.latitudeDMS(currentLocation.getLatitude()));
        txtCoords2.setText(GeoLocFormat.longitudeDMS(currentLocation.getLongitude()));
    }

    /**
     * Initializes the localisation
     */
    @SuppressLint("MissingPermission")
    private void initLocation(){
        LocationManager manager =
                (LocationManager)getSystemService(LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, this);
    }

    /**
     * Update the localisation
     * @param location
     */
    @Override
    public void onLocationChanged(@NonNull android.location.Location location) {
        currentLocation.setLongitude((float) location.getLongitude());
        currentLocation.setLatitude((float) location.getLatitude());
        ShowLocation(currentLocation);
    }

    /**
     * Get the real Date
     * @param day
     * @param hour
     * @return
     */
    private String realDay(int day, int hour){
        Calendar c = Calendar.getInstance();
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,
                getResources().getConfiguration().locale);
        c.setTime(new Date());
        c.add(Calendar.DATE, day);
        c.set(Calendar.HOUR_OF_DAY,hour);
        return df.format(c.getTime())+String.format(" - %02d h",
                c.get(Calendar.HOUR_OF_DAY));
    }


    /**
     * Ask for permission for localisation
     * @param requestCode
     * @param permissions
     * @param results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[]
            permissions, int[] results) {
        super.onRequestPermissionsResult(requestCode, permissions, results);
        initLocation();
    }


}