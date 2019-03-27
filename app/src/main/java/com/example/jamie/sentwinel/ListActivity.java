package com.example.jamie.sentwinel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    PieChart chart;
    TextView textTitle;

    String keyword;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Assigns variables to the xml values.
        chart = (PieChart) findViewById(R.id.chart);
        textTitle = (TextView) findViewById(R.id.pie_chart_title);

        //Gets the values from MainActivity
        Intent intent = getIntent();
        keyword = intent.getStringExtra("EXTRA_TEXT");
        number = intent.getStringExtra("EXTRA_NUMBER");

        //Aesthetic and formatting settings
        chart.setHoleRadius(30f);
        chart.setCenterText(keyword);
        chart.setCenterTextSize(20);
        chart.setTransparentCircleAlpha(0);
        textTitle.setText("How Happy/Sad is everyone from " + number + " tweets?");


        getPolarity();
    }

    private void getPolarity() {
        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);


        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<List<Polarity>> call = api.getPolarity(keyword, number);




        call.enqueue(new Callback<List<Polarity>>() {
            @Override
            public void onResponse(Call<List<Polarity>> call, Response<List<Polarity>> response) {


                List<Polarity> happyList = response.body();

                List<PieEntry> entries = new ArrayList<>();

                //For loop getting the values from the API and applying them to the relevant labels in the chart.
                for (int i = 0; i < happyList.size(); i++) {
                    entries.add(new PieEntry(happyList.get(i).getPositive(), "% Positive"));
                    entries.add(new PieEntry(happyList.get(i).getNegative(), "% Negative"));
                    entries.add(new PieEntry(happyList.get(i).getNeutral(), "% Neutral"));
                }

                //Sets the values and display of the pie chart. Colours, labels etc.
                PieDataSet set = new PieDataSet(entries, "Polarity Results");
                set.setColors(new int[] { R.color.colorGreen, R.color.colorRed, R.color.colorYellow}, ListActivity.this);
                set.setValueTextSize(20);
                set.setFormSize(20);
                set.setSelectionShift(10);
                PieData data = new PieData(set);
                chart.setData(data);
                chart.invalidate(); // refresh



            }

            @Override
            public void onFailure(Call<List<Polarity>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

