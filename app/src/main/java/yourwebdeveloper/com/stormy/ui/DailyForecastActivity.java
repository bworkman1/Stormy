package yourwebdeveloper.com.stormy.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import yourwebdeveloper.com.stormy.R;
import yourwebdeveloper.com.stormy.adapters.DayAdapter;
import yourwebdeveloper.com.stormy.weather.Day;

public class DailyForecastActivity extends ListActivity {

    private Day[] mDays;
    @Bind(R.id.locationLabel) TextView mArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                "yourwebdeveloper.com.stormy", Context.MODE_PRIVATE);
        String area = prefs.getString("area", "");
        if(area.isEmpty()) {
            area = "Utica, OH";
        }
        mArea.setText(area);

        DayAdapter adapter = new DayAdapter(this, mDays);
        setListAdapter(adapter);
    }

}
