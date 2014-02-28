package com.sivitsky.ruslan.service.impl;

import com.google.gson.Gson;
import com.sivitsky.ruslan.service.TranslateService;
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
 */ /*
class GsonAnswer {
    private int code;
    private String lang;


    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
*/
@Service
public class TranslateServiceImpl implements TranslateService {

    public static final String YANDEX_BASE_URL = "https://translate.yandex.net";
    public static final String YANDEX_KEY = "trnsl.1.1.20140211T084212Z.6d755fd3df9c1609.b4793690d02bea684c940bcf339050722c4757f8";
    public static final String YANDEX_V2_URL = "https://translate.yandex.net/api/v1.5/tr/translate?key={key}&lang={lang}&text={text}";
    //public static final String TRANSLATE_API = "/api/v1.5/tr/translate";
    public static final String TRANSLATE_API = "api/v1.5/tr.json/translate";

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
    public Properties translateProp(String dest_lang, Properties p) throws NoSuchFieldException {
        Properties properties = new Properties();
        Set keys;
        // StringBuffer stringBuffer = new StringBuffer();
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


    public String v1(String dest_lang, String original_text) throws NoSuchFieldException {

        Gson gson = new Gson();
        URI targetUrl = UriComponentsBuilder.fromUriString(YANDEX_BASE_URL)
                .path(TRANSLATE_API)
                .queryParam("key", YANDEX_KEY)
                .queryParam("lang", dest_lang)
                .queryParam("text", original_text)
                .build()
                .toUri();

        String br = new RestTemplate().getForObject(targetUrl, String.class);
        // GsonAnswer gsonAnswer = gson.fromJson(br, GsonAnswer.class);
        //return new RestTemplate().getForObject(targetUrl, String.class);
        gson = gson.fromJson(br, Gson.class);
        String fieldname = "text";
        return gson.getClass().getField(fieldname).toString();
    }

    private String v2(final String original_lang, final String dest_lang, final String original_text) {
        return new RestTemplate().getForObject(YANDEX_V2_URL, String.class, new HashMap<String, String>() {{
            put("key", YANDEX_KEY);
            put("lang", original_lang + "-" + dest_lang);
            put("text", original_text);
        }});
    }

    public static void main(String[] args) throws NoSuchFieldException {


        Properties properties = new Properties();
        properties.put("color", "red");
        properties.put("size", "big");
        properties.put("weight", "ass");

        Properties properties1 = new TranslateServiceImpl().translateProp("ru", properties);

        properties.list(System.out);
        properties1.list(System.out);

        // System.out.println(properties1.getProperty("color"));

        // System.out.println(new TranslateServiceImpl().v1("en", "ru", "hello"));
        // System.out.println(new TranslateServiceImpl().v2("en", "ru", "hello"));
    }
}
