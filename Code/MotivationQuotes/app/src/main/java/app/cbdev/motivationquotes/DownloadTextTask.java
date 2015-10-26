package app.cbdev.motivationquotes;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Chen on 01/10/2015.
 */
public class DownloadTextTask extends AsyncTask<String, Void, ArrayList<String>> {
    private ArrayList<String> q;

    public DownloadTextTask(ArrayList<String> quotes) {
        this.q = (ArrayList) quotes.clone();
    }

    @Override
    protected ArrayList<String> doInBackground(String... urls) {
        try {
            //create url object to point to the file location on internet
            URL url = new URL(urls[0]);

            //create BufferedReader object
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            //read content of the file line by line
            while ((line = br.readLine()) != null) {
                if (!q.contains(line))
                    q.add(line);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }
}