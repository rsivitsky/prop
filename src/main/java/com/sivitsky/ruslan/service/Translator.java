package com.sivitsky.ruslan.service;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.Set;

public class Translator {


    public Properties TranslateProp(Properties p) {
        //пока тупо берем ключи пропертис и вставляем в пропертис якобы переведенное
        Properties properties = new Properties();
        Set keys;
        keys = p.keySet();
        for (Object key : keys) {
            properties.put(key, "");
        }
        return properties;
    }


    public String PropertiesToString(Properties p) throws IOException {
        String s = "";
        Writer outWriter = new StringWriter();
        p.store(outWriter, s);
        return outWriter.toString();
    }


    public Properties StringToProperties(String s) throws IOException {
        final Properties p = new Properties();
        p.load(new StringReader(s));
        return p;
    }


    public String retrievesSourceToDest(String original_lang, String dest_lang, String original_text) {
        return new RestTemplate().getForObject("http://syslang.com?src={original_lang}&dest={dest_lang}&text=" +
                "{original_text}&email=rsivitsky@yandex.ru&password=richard",
                String.class, original_lang, dest_lang, original_text);
    }
}
