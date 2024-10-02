import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentIPHandler {
    public static String getCurrentIP() throws IOException {
        Process process = Runtime.getRuntime().exec("ipconfig");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String ipRegex = "(\\d{1,3}\\.){3}\\d{1,3}";

        Pattern pattern = Pattern.compile(ipRegex);
        String line;
        String ip = "";
        while ((line = reader.readLine())!= null){
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()){

                ip = matcher.group();
                break;
            }
        }
        return ip;
    }
}
