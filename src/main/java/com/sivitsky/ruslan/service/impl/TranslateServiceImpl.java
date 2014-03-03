package com.sivitsky.ruslan.service.impl;

import com.google.gson.Gson;
import com.sivitsky.ruslan.service.TranslateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

/**
 * @author Paul Jakimov
 *         Date: 1/28/14
 *         Time: 7:18 PM
 */

@Service
public class TranslateServiceImpl implements TranslateService {

    @Value("${yandex_base_url}")
    private String yandex_base_url;

    @Value("${yandex_key}")
    private String yandex_key;

    @Value("${translate_api}")
    private String translate_api;


    public String getYandex_base_url() {
        return yandex_base_url;
    }

    public String getYandex_key() {
        return yandex_key;
    }

    public String getTranslate_api() {
        return translate_api;
    }

    //public static final String YANDEX_BASE_URL = "https://translate.yandex.net";
    //public static final String YANDEX_KEY = "trnsl.1.1.20140211T084212Z.6d755fd3df9c1609.b4793690d02bea684c940bcf339050722c4757f8";
    public static final String YANDEX_V2_URL = "https://translate.yandex.net/api/v1.5/tr/translate?key={key}&lang={lang}&text={text}";
    //public static final String TRANSLATE_API = "/api/v1.5/tr/translate";
    //public static final String TRANSLATE_API = "api/v1.5/tr.json/translate";


    class GsonAnswer {
        private int code;
        private String lang;


        private String text;

        public String getText() {
            return text;
        }
    }

    @Override
    public String translateLine(String src, String dest, String line) {
        line = dest;
        return line.concat("!!!");
    }

    @Override
    public Properties stringToProperties(String s) throws IOException {
        final Properties p = new Properties();
        p.load(new StringReader(s));
        return p;
    }

    @Override
    public String propertiesToString(Properties p) throws IOException {
        String s = "";
        Writer outWriter = new StringWriter();
        p.store(outWriter, s);
        return outWriter.toString();
    }

    @Override
    public Properties translateProp(String dest_lang, Properties p) {
        Properties properties = new Properties();
        Set keys;
        keys = p.keySet();
        for (Object key : keys) {
            //properties.put(key, p.getProperty(key.toString()));
            properties.put(key, v1(dest_lang, p.getProperty(key.toString())));
            //properties.setProperty(key.toString(), p.getProperty(key.toString()) + "!!!");
        }
        return properties;
    }

   /* @Override
    public String retrievesSourceToDest(String original_lang, String dest_lang, String original_text) {
        return v1(original_lang, dest_lang, original_text);
    }*/


    public String v1(String dest_lang, String original_text) {


        Gson gson = new Gson();
        URI targetUrl = UriComponentsBuilder.fromUriString(getYandex_base_url())
                .path(getTranslate_api())
                .queryParam("key", getYandex_key())
                .queryParam("lang", dest_lang)
                .queryParam("text", original_text)
                .build()
                .toUri();

        String br = new RestTemplate().getForObject(targetUrl, String.class);
        GsonAnswer gsonAnswer = gson.fromJson(br, GsonAnswer.class);

        return gsonAnswer.getText();
    }

    private String v2(final String original_lang, final String dest_lang, final String original_text) {
        return new RestTemplate().getForObject(YANDEX_V2_URL, String.class, new HashMap<String, String>() {{
            put("key", translate_api);
            put("lang", original_lang + "-" + dest_lang);
            put("text", original_text);
        }});
    }

    public static void main(String[] args) throws NoSuchFieldException {


        Properties properties = new Properties();
        properties.put("color", "red");
        properties.put("size", "big");
        properties.put("weight", "ass");

        Properties properties1 = null;

        properties1 = new TranslateServiceImpl().translateProp("ru", properties);


        properties.list(System.out);
        properties1.list(System.out);

        // System.out.println(properties1.getProperty("color"));

        // System.out.println(new TranslateServiceImpl().v1("en", "ru", "hello"));
        // System.out.println(new TranslateServiceImpl().v2("en", "ru", "hello"));
    }
}
