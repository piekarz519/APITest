package api.piekarnia.com.apitest;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



class RetrieveFeedTask extends AsyncTask<Void, Void, String> {
    static final String API_KEY = "dd2adf9987792f911beec317c185ad86";
    static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String TAG_TEMP = "temp";
    private static final String TAG_PRESSURE = "pressure";
    private static final String TAG_WIND_SPEED = "speed";
    private static final String TAG_WEATHER_ID="id";
    private static final String TAG_WEATHER_MAIN="main";
    private static final String TAG_WEATHER_DESCRIPTION="description";
    private static final String TAG_WEATHER_ICON="icon";
    public Activity activity;
    TextView responseView;
    ProgressBar progressBar;
    String city;
    public RetrieveFeedTask(Activity activity,String _city) {
        city=_city;
        this.activity=activity;
        responseView=(TextView)this.activity.findViewById(R.id.responseView);
        progressBar=(ProgressBar)this.activity.findViewById(R.id.progressBar);
    }
    protected void onPreExecute() {

        progressBar.setVisibility(View.VISIBLE);
        responseView.setText("");

    }

    protected String doInBackground(Void... urls) {
        String link=API_URL+city+"&APPID="+API_KEY;

        // Do some validation here   http://api.openweathermap.org/data/2.5/weather?q=London&APPID=dd2adf9987792f911beec317c185ad86
        try {
            URL url = new URL(link);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if (response == null) {
            response = "THERE WAS AN ERROR";
        }
        progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);
        responseView.setText(response);
    }
}
