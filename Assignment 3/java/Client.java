import java.util.Map;
import java.util.HashMap;

import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class Client {
    // Perform a http GET request from the specified URL.
    // You can add error checking, such as page not found using exceptions
    public static String get(String urlName) throws Exception {
        URL url = new URI(urlName).toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String reply = "";
        String line;
        while ((line = in.readLine()) != null) {
            reply += line + "\n";
        }
        in.close();
        return reply;
    }

    // Perform a http POST request from the specified URL and pass in the data string.
    // You can add error checking, such as page not found using exceptions
    public static String post(String urlName, String data) throws Exception {
        URL url = new URI(urlName).toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        new DataOutputStream(con.getOutputStream()).write(data.getBytes("UTF-8"));
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String reply = "";
        String line;
        while ((line = in.readLine()) != null) {
            reply += line + "\n";
        }
        in.close();
        return reply;
    }


    public static void main(String[] args) throws Exception {
        // The following shows example code requesting each of the example functions from the server
        System.out.println(get("http://localhost:2250/"));

        System.out.println(post("http://127.0.0.1:2250/hello", "Alice"));

        System.out.println(get("http://127.0.0.1:2250/redirect"));

        System.out.println(get("http://127.0.0.1:2250/visitors?name=Frank"));

        // More complicated responses get translated into a json string, so you may want to use an external
        // library such as org.json for easier reading.
        System.out.println(get("http://127.0.0.1:2250/json"));

        
        System.out.println(post("http://127.0.0.1:2250/add_expense", "{\"value\": \"test1\"}"));
        System.out.println(post("http://127.0.0.1:2250/audit_expenses", "{\"value\": \"test1\"}"));

    }
}
