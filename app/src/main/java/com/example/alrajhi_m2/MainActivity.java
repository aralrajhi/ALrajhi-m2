package com.example.alrajhi_m2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button btnActivity2;
    TextView txtDate,txtTemp,txtHumid;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker =  findViewById(R.id.datePicker);
        txtDate = findViewById(R.id.txtDate);
        txtTemp = findViewById(R.id.txtTemperature);
        btnActivity2 = findViewById(R.id.btnActivity2);
        txtHumid = findViewById(R.id.txtHumid);

        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                String day = ""+datePicker.getDayOfMonth();
                String month =  ""+ (datePicker.getMonth() + 1);
                String year = ""+ datePicker.getYear();

                txtDate.setText(day+"-"+month+"-"+year);
            }
        });

        getWeather("http://api.openweathermap.org/data/2.5/weather?q=London&appid=76b4f5fc92f432d9fc68dffc349aa1cf&units=metric");

        Toast.makeText(this,"Welcome to the project of Aram, 201579",Toast.LENGTH_SHORT).show();

        Toast.makeText(this,"Welcome to the project of Aram, 201572",Toast.LENGTH_SHORT).show();


//        final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
//        mp.start();
//        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                mp.release();
//            }
//        });
    }

    public void getWeather(String url){
            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject jsonMain = response.getJSONObject("main");
                JSONObject jsonSys = response.getJSONObject("sys");

                double temp = jsonMain.getDouble("temp");
                txtTemp.setText(String.valueOf(temp)+"°C");


                double humid = jsonMain.getDouble("humidity");
                txtHumid.setText("Humidity: "+String.valueOf(humid));

            }
            catch (JSONException e){
                e.printStackTrace();
                Log.e("Receive Error", e.toString());
            }
            }, error -> Log.d("Aram", "Error Retrieving URL"));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }
}
