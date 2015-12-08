package yourwebdeveloper.com.stormy.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import yourwebdeveloper.com.stormy.R;

public class AppSettings extends AppCompatActivity {

    Button mSaveBtn;
    String mSaveZip;

    @Bind(R.id.zipCodeInput) EditText mUserZip;

    private Context context;
    private final static String STORETEXT="zip.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        ButterKnife.bind(this);

        SharedPreferences prefs = this.getSharedPreferences(
                "yourwebdeveloper.com.stormy", Context.MODE_PRIVATE);
        String userDefinedZip = prefs.getString("zip", null);

        if(userDefinedZip != null) {
            mUserZip.setText(userDefinedZip);
        }
        mSaveBtn = (Button)findViewById(R.id.saveZipButton);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                EditText zip = (EditText)findViewById(R.id.zipCodeInput);
                mSaveZip = zip.getText().toString();
                if(isZipValid(mSaveZip)) {
                    saveZipCode(mSaveZip);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Zip Code", Toast.LENGTH_LONG).show();
                }

                Log.d("TAG", "Zip Entered: "+mSaveZip);
            }
        });


    }

    public boolean isZipValid(String zipCode) {
        if(zipCode.length()!=5) {
            return false;
        }
        try {
            double d = Double.parseDouble(zipCode);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void saveZipCode(String zipCode) {
        try {
            Toast.makeText(getApplicationContext(), "Your zip code has been saved!", Toast.LENGTH_LONG).show();
            getLocationCords(zipCode);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch(Throwable t) {
            Toast.makeText(getApplicationContext(), "Something went wrong saving your zip, try again", Toast.LENGTH_LONG).show();
        }
    }

    private void getLocationCords(String zipCode) {
        SharedPreferences prefs = this.getSharedPreferences(
                "yourwebdeveloper.com.stormy", Context.MODE_PRIVATE);
        prefs.edit().putString("zip", zipCode).apply(); //Save zip to settings

        String url = "http://maps.googleapis.com/maps/api/geocode/json?address="+zipCode+"&sensor=true";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                Toast.makeText(getApplicationContext(), "Error Fetching Data", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        try {
                            JSONObject data = new JSONObject(jsonData);
                            JSONObject location = data.getJSONArray("results").getJSONObject(0);
                            String area = location.getString("formatted_address");
                            JSONObject geometry = location.getJSONObject("geometry");
                            JSONObject cords = geometry.getJSONObject("location");

                            String lat = cords.getString("lat");
                            String lon = cords.getString("lng");

                            Log.d("TAG", "TEST 1: " + lat);
                            Log.d("TAG", "TEST 1.2: "+lon);

                            area = area.substring(0, area.length() - 11);

                            SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                                    "yourwebdeveloper.com.stormy", Context.MODE_PRIVATE);
                            prefs.edit().putString("latitude", lat).apply();
                            prefs.edit().putString("longitude", lon).apply();
                            prefs.edit().putString("area", area).apply();

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error Fetching Data", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Error Fetching Data", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    Log.e("TAG", "Exception caught: ", e);
                    Toast.makeText(getApplicationContext(), "Error Fetching Data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
