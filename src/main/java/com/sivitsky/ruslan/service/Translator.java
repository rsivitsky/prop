package com.sivitsky.ruslan.service;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Translator {


    public Properties translateProp(Properties p) {
        //пока тупо берем ключи пропертис и вставляем в пропертис якобы переведенное
        Properties properties = new Properties();
        Set keys;
        StringBuffer stringBuffer = new StringBuffer();
        keys = p.keySet();
        String klichnik = "!!!";
        for (Object key : keys) {
            properties.put(key, p.getProperty(key.toString()) + klichnik);
            //properties.setProperty(key.toString(), p.getProperty(key.toString()) + "!!!");
        }
        return properties;
    }


    public String propertiesToString(Properties p) throws IOException {
        String s = "";
        Writer outWriter = new StringWriter();
        p.store(outWriter, s);
        return outWriter.toString();
    }


    public Properties stringToProperties(String s) throws IOException {
        final Properties p = new Properties();
        p.load(new StringReader(s));
        return p;
    }


    public String retrievesSourceToDest(String original_lang, String dest_lang, String original_text) {
        Map<String, String> params = new HashMap<>();
        params.put("email", "rsivitsky@yandex.ru");
        params.put("password", "richard");

        params.put("src", original_lang);
        params.put("dest", dest_lang);
        params.put("text", original_text);
        return new RestTemplate().getForObject("http://syslang.com", String.class, params);
    }

}
