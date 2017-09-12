package ayman.thewittyresponder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOnClick(View view) throws Exception {
        TextView question = (TextView) findViewById(R.id.multiAutoCompleteTextView2);
        getAnswer("q3");

    }

//    public static void main() {
//
//        Runnable r = new Runnable() {
//            public void run() {
//                test tester = new test();
//                try {
//                    getAnswer("q3");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.i("MainActivity", "exception: " + e);
//                }
//                finally {
//                    Log.i("MainActivity", "well this part works: "  );
//                }
//            }
//        };
//
//        new Thread(r).start();
//        //this line will execute immediately, not waiting for your task to complete
//        try {
//            getAnswer("q3");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public static void getAnswer(String question) throws Exception {

        URL url = new URL("http://192.168.1.2/Android/includes/getData.php"); // URL to your application
        Map<String, String> params = new LinkedHashMap<>();
        params.put("question", "q2"); // All parameters, also easy

        StringBuilder postData = new StringBuilder();
        // POST as urlencoded is basically key-value pairs, as with GET
        // This creates key=value&key=value&... pairs
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        // Convert string to byte array, as it should be sent
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        // Connect, easy
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Tell server that this is POST and in which format is the data
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);

        // the following line is where it fails
        conn.getOutputStream().write(postDataBytes);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();

    }
}

