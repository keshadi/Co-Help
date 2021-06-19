package com.example.co_help;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.co_help.api.ApiUtilities;
import com.example.co_help.api.CountryData;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView totalConfirmed, todayConfirmed, totalActive, totalRecovered, todayRecovered;
    TextView totalDeath, todayDeath, date;
    PieChart pieChart;

    private List<CountryData> list;
    String country = "India";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        if(getIntent().getStringExtra("country") != null)
            country = getIntent().getStringExtra("country");

        init();

        TextView cname = findViewById(R.id.cname);
        cname.setText(country);

        cname.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CountryActivity.class)));



        ApiUtilities.getApiInterface().getCountryData()
                .enqueue(new Callback<List<CountryData>>() {
                    @Override
                    public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                        assert response.body() != null;
                        list.addAll(response.body());

                        for(int i=0; i<list.size(); i++){
                            if(list.get(i).getCountry().equals(country)){
                                int confirmed =  Integer.parseInt(list.get(i).getConfirmed());
                                int active =  Integer.parseInt(list.get(i).getActive());
                                int recovered =  Integer.parseInt(list.get(i).getRecovered());
                                int death =  Integer.parseInt(list.get(i).getDeaths());

                                totalConfirmed.setText(NumberFormat.getInstance().format(confirmed));
                                totalActive.setText(NumberFormat.getInstance().format(active));
                                totalRecovered.setText(NumberFormat.getInstance().format(recovered));
                                totalDeath.setText(NumberFormat.getInstance().format(death));

                                todayConfirmed.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getDeltaconfirmed())));
                                todayRecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getDeltarecovered())));
                                todayDeath.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getDeltadeaths())));

                                setText(list.get(i).getLastupdatedtime());


                                pieChart.addPieSlice(new PieModel( "Confirmed", confirmed, getColor(R.color.yellow)));
                                pieChart.addPieSlice(new PieModel( "Active", active, getColor(R.color.blue)));
                                pieChart.addPieSlice(new PieModel( "Recovered", recovered, getColor(R.color.green_pie)));
                                pieChart.addPieSlice(new PieModel( "Confirmed", death, getColor(R.color.red_pie)));

                                pieChart.startAnimation();
                            }
                        }

                    }


                    @Override
                    public void onFailure(Call<List<CountryData>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT);
                    }
                });

    }

    private void setText(String lastupdatedtime) {
        DateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        long milliseconds = Long.parseLong(lastupdatedtime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        date.setText("Updated at "+format.format(calendar.getTime()));
    }


    private void init() {
        totalConfirmed = findViewById(R.id.totalConfirmed);
        todayConfirmed = findViewById(R.id.todayConfirmed);
        totalActive = findViewById(R.id.totalActive);
        totalRecovered = findViewById(R.id.totalRecovered);
        todayRecovered = findViewById(R.id.todayRecovered);
        totalDeath = findViewById(R.id.totalDeath);
        todayDeath = findViewById(R.id.todayDeath);
        pieChart = findViewById(R.id.piechart);
        date = findViewById(R.id.date);
    }

}