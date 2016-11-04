package com.example.dorinpaunescu.testapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.List;

import retrofit.Callback;
import retrofit.ResponseCallback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        for(Sensor sensor : sensorList) {
            mSensorManager.registerListener(new SensorActivity((TextView)findViewById(R.id.textViewX), (TextView)findViewById(R.id.textViewY), (TextView)findViewById(R.id.textViewZ)), sensor, SensorManager.SENSOR_DELAY_NORMAL);
            break;
        }

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("https://jsonplaceholder.typicode.com")
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .build();
                InterfaceRest communicatorInterface = restAdapter.create(InterfaceRest.class);

                Callback<Response> callback = new Callback<Response>() {

                    @Override
                    public void success(Response response, Response response2) {
                        int status = response.getStatus();

                        System.out.println("Status: " + status);

                        TypedByteArray body = (TypedByteArray) response.getBody();
                        String outputStr = new String(body.getBytes());
                        System.out.print(outputStr);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                };
                communicatorInterface.getData(callback);
            }
        });
    }


}
