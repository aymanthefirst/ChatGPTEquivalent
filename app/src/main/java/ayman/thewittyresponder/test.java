package ayman.thewittyresponder;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by aymanharake on 28/08/2017.
 */

class test extends AsyncTask<Void, Void, Void>
{



    public void getAnswer(String question) throws Exception {

        URL url = new URL("http://192.168.1.2/Android/includes/getData.php"); // URL to your application
        Map<String,String> params = new LinkedHashMap<>();
        params.put("question", "q2"); // All parameters, also easy

        StringBuilder postData = new StringBuilder();
        // POST as urlencoded is basically key-value pairs, as with GET
        // This creates key=value&key=value&... pairs
        for (Map.Entry<String,String> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        // Convert string to byte array, as it should be sent
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        // Connect, easy
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        // Tell server that this is POST and in which format is the data
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        Log.i("MainActivity", "testing1: ");



            conn.getOutputStream().write(postDataBytes);
            Log.i("MainActivity", "testing2: ");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
            Log.i("MainActivity", "exception: " );
            Log.i("MainActivity", "that didnt work: " );
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //this method will be running on UI thread
    }
    @Override
    protected Void doInBackground(Void... params) {

        //this method will be running on background thread so don't update UI frome here
        //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here


        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        //this method will be running on UI thread

    }

}

