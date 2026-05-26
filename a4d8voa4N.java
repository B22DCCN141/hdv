package hdv;
import java.util.*;
import java.io.*;
import java.net.*;
public class a4d8voa4N {
    // File -> Project Properties -> Run -> VM options -> --add-opens java.base/java.net=ALL-UNNAMED
    public static void main(String args[]) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) URI.create("http://36.50.135.242:2230/api/rest/method?studentCode=B22DCCN141&qCode=4d8voa4N").toURL().openConnection();
        conn.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String json = "", line;
        while((line = br.readLine()) != null){
            json += line;
        }
        System.out.println(json);
        br.close();
        String rq = json.split("\"requestId\":\"")[1].split("\"")[0];
        String etag = json.split("\"etag\":\"")[1].split("\"\\}\\}")[0];
        etag = etag.replace("\\", "");
        System.out.println(etag);
        HttpURLConnection patchConn = (HttpURLConnection) URI.create("http://36.50.135.242:2230/api/rest/method/" + rq).toURL().openConnection();
        java.lang.reflect.Field methodField = HttpURLConnection.class.getDeclaredField("method");
        methodField.setAccessible(true);
        methodField.set(patchConn, "PATCH");
        patchConn.setRequestProperty("Content-Type", "application/json");
        patchConn.setRequestProperty("If-Match", etag);
        patchConn.setDoOutput(true);
        String body =
                "{"
                + "\"studentCode\":\"B22DCCN141\","
                + "\"qCode\":\"4d8voa4N\","
                + "\"answer\":{"
                + "\"status\":\"RESOLVED\""
                + "}"
                + "}";
        OutputStream out = patchConn.getOutputStream();
        out.write(body.getBytes());
        out.close();
        BufferedReader patchBr = new BufferedReader(new InputStreamReader(patchConn.getInputStream()));
        while((line = patchBr.readLine()) != null){
            System.out.println(line);
        }
        patchBr.close();
    }
}
