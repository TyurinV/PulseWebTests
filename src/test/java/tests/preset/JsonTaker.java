package tests.preset;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v113.network.Network;

import java.io.*;
import java.net.URL;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.sleep;

public class JsonTaker {
    public static String getPresetUrl() {
        return presetUrl;
    }

    private static String presetUrl;

    public String getPresetJson(String url) {
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();

        devTools.createSession();
        devTools.send(Network.enable(Optional.of(1000000), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            String responseUrl = responseReceived.getResponse().getUrl();
            if (responseUrl.contains("/presets/")) {
                presetUrl = responseUrl;
            }
        });
        driver.get(url);
        sleep(1000);
        driver.close();
        return presetUrl;
    }

    public static String stream(String string) {
        URL url;

        try {
            url = new URL(string);
            InputStream input = url.openStream();
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
