package hdv;
import java.io.*;
import java.net.*;
import javax.crypto.*;
import javax.crypto.spec.*;
public class JvF6TmEk {

    public static void main(String args[]) throws Exception {
        HttpURLConnection conn = (HttpURLConnection)URI.create("http://36.50.135.242:2230/api/rest/header?studentCode=B22DCCN141&qCode=JvF6TmEk").toURL().openConnection();
        conn.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String json = "", line;
        while((line = br.readLine()) != null){
            json += line;
        }
        System.out.println(json);
        br.close();
        String rq = json.split("\"requestId\":\"")[1].split("\"")[0];
        String nonce = json.split("\"nonce\":\"")[1].split("\"")[0];
        String signingKey = json.split("\"signingKey\":\"")[1].split("\"")[0];
        String events = json.split("\"events\":\\[\"")[1].split("\"\\]")[0];
        events = events.replace("\",\"", "|");
        System.out.println(events);
        String payload = nonce + ":" + events + ":B22DCCN141";
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(signingKey.getBytes(), "HmacSHA256");
        mac.init(secretKey);
        byte[] hash = mac.doFinal(payload.getBytes());
        String signature = "";
        for(byte b: hash){
            signature += String.format("%02x", b);
        }
        System.out.println(signature);
        HttpURLConnection postConn = (HttpURLConnection) URI.create("http://36.50.135.242:2230/api/rest/header/submit").toURL().openConnection();
        postConn.setRequestMethod("POST");
        postConn.setRequestProperty("X-Signature", signature);
        postConn.setRequestProperty("Content-Type", "application/json");
        postConn.setDoOutput(true);
        String body = 
                "{"
                + "\"studentCode\": \"B22DCCN141\","
                + "\"qCode\": \"JvF6TmEk\","
                + "\"requestId\": \"" + rq + "\""
                + "}";
        OutputStream out = postConn.getOutputStream();
        out.write(body.getBytes());
        out.close();
        BufferedReader postBr = new BufferedReader(new InputStreamReader(postConn.getInputStream()));
        while((line = postBr.readLine()) != null){
            System.out.println(line);
        }
        postBr.close();
    }
}
