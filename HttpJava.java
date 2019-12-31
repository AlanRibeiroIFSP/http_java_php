package httpjava;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class HttpJava {

    public static void main(String[] args) throws IOException, Exception {

        String url = "http://localhost/servidor.php";

        String nome = "José";
        String sobrenome = "Ribeiro";
        String param = "nome= " + nome
                + "&sobrenome= " + sobrenome
                + "&idade= 55";

        JSONObject obj = new JSONObject(new HttpJava().Post(url, param));

        System.out.println("nome.....: " + obj.getString("nome"));
        System.out.println("sobrenome: " + obj.getString("sobrenome"));
        System.out.println("idade....: " + obj.getInt("idade"));

    }

    //--------------------------------------------------------------------------
    private static HttpURLConnection con;

    public String Post(String url, String urlParameters) throws Exception {

        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {
            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content;

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            return content.toString();

        } catch (Exception e) {
            return "{'nome':'vazio','sobrenome':'sem', 'idade': 0}";
            
        } finally {
            con.disconnect();
        }
    }
}
