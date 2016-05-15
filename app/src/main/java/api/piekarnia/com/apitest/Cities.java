package api.piekarnia.com.apitest;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leny on 13.05.2016.
 */
public class Cities extends IOException{

    public static List<String> readRawTextFile(Context ctx, int resId)
    {
        List<String> cities=new ArrayList<String>();
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;


        try {
            while (( line = buffreader.readLine()) != null) {
                cities.add(line);
            }
        } catch (IOException e) {
            return null;
        }
        return cities;
    }
}
