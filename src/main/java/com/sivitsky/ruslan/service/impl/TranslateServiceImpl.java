package com.sivitsky.ruslan.service.impl;

import com.google.gson.Gson;
import com.sivitsky.ruslan.service.TranslateService;
import com.sivitsky.ruslan.service.response.TranslationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.util.Properties;
import java.util.Set;

/**
 * @author Paul Jakimov
 *         Date: 1/28/14
 *         Time: 7:18 PM
 */

@Service
public class TranslateServiceImpl implements TranslateService {

    @Value("${yandex.base.url}")
    private String yandexBaseUrl;

    @Value("${yandex.key}")
    private String yandexKey;

    @Value("${yandex.translate.api}")
    private String YandexTranslateApi;

    private Gson gson = new Gson();

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
    public Properties translateProperties(String destinationLang, Properties p) {
        Properties properties = new Properties();
        Set keys = p.keySet();
        for (Object key : keys) {
            properties.put(key, v1(destinationLang, p.getProperty(key.toString())));
        }
        return properties;
    }

    public String v1(String destinationLang, String originalText) {
        URI targetUrl = UriComponentsBuilder.fromUriString(yandexBaseUrl)
                .path(YandexTranslateApi)
                .queryParam("key", yandexKey)
                .queryParam("lang", destinationLang)
                .queryParam("text", originalText)
                .build()
                .toUri();

        String br = new RestTemplate().getForObject(targetUrl, String.class);

        TranslationResponse response = gson.fromJson(br, TranslationResponse.class);
        return response.getText();
    }

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.put("color", "red");
        properties.put("size", "big");
        properties.put("weight", "ass");
        TranslateService translateService = new TranslateServiceImpl();
        Properties result = translateService.translateProperties("ru", properties);

        properties.list(System.out);
        result.list(System.out);
    }
}
