package com.example.lhenryaxelmeteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

    private FakeForecast forecast;

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

        forecast = new FakeForecast();
        
        
    }

    private void OnClickSearch(View view) {
        Location l = new Location();
        l.setCity(editVille.toString());
        l.setLatitude((float) 47.311);
        l.setLongitude((float) 5.069);
        ShowLocation(l);
        ShowWeather(forecast.getForecast(l).getForecast(0));
    }

    private void ShowWeather(Weather weather){
        txtTemperature.setText("Température : " + String.valueOf(weather.getTemperature()));
        txtHumidity.setText("Humidité : " + String.valueOf(weather.getHumidity()));
        txtWindDirection.setText("Vitesse du vent : " + weather.getWindDirection());
        txtWindSpeed.setText("Direction du vent : " + String.valueOf(weather.getWindSpeed()));
        txtPrecipitation.setText("Précipitations : " + String.valueOf(weather.getPrecipitation()));
        txtDate.setText(String.valueOf(weather.getDay()));
    }

    private void ShowLocation(Location location){
        GeoLocFormat format = new GeoLocFormat();
        txtCoords1.setText(GeoLocFormat.latitudeDMS(location.getLatitude()));
        txtCoords2.setText(GeoLocFormat.longitudeDMS(location.getLongitude()));
    }
}