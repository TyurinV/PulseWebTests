package tests.preset;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.preset.JsonTaker.getPresetUrl;
import static tests.preset.JsonTaker.stream;


@ExtendWith({AllureJunit5.class})
public class TestPresets {

    String url = "https://web-lk-pulse-dev.website.cloud.croc.ru";

    @Test
    void dachnikTest() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=pets&preset=dachnik&personalization=4-paws_base");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/4-paws/dachnik.json")));
    }

    @Test
    void liteTest() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=pets&preset=lite&personalization=4-paws_base");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/4-paws/lite.json")));
    }

    @Test
    void optimumTest() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=pets&preset=optimum&personalization=4-paws_base");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/4-paws/lite.json")));
    }

    //dubleDOM
    @Test
    void topol27Test() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=house&clientCategory=VIP&preset=topol27&region=0c5b2444-70a0-4932-980c-b4dc0d3f02b5");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/duble/topol27.json")));
    }

    @Test
    void kedr65Test() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=house&clientCategory=VIP&preset=kedr65&region=0c5b2444-70a0-4932-980c-b4dc0d3f02b5");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/duble/kedr65.json")));
    }

    @Test
    void kashtan77Test() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=house&clientCategory=VIP&preset=kashtan77&region=0c5b2444-70a0-4932-980c-b4dc0d3f02b5");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/duble/kashtan77.json")));
    }

    @Test
    void baobab103Test() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url +"/?product=house&clientCategory=VIP&preset=baobab103&region=0c5b2444-70a0-4932-980c-b4dc0d3f02b5");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/duble/baobab103.json")));
    }

    @Test
    void sequoia140Test() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=house&clientCategory=VIP&preset=sequoia140&region=0c5b2444-70a0-4932-980c-b4dc0d3f02b5");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/duble/sequoia140.json")));
    }

    //pets
    @Test
    void chinchillaLuxTest() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=pets&preset=chinchilla-lux");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/pet/chinchilla-lux.json")));
    }

    @Test
    void chinchillaOptimumTest() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=pets&preset=chinchilla-optimum");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/pet/chinchilla-optimum.json")));
    }

    @Test
    void ferretLuxTest() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=pets&preset=ferret-lux");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/pet/ferret-lux.json")));
    }

    @Test
    void ferretOptimumTest() throws IOException {
        JsonTaker jsonTaker = new JsonTaker();
        jsonTaker.sometest(url + "/?product=pets&preset=ferret-optimum");
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(stream(getPresetUrl())), mapper.readTree(new File("src/test/resources/config/preset/pet/ferret-optimum.json")));
    }


}
