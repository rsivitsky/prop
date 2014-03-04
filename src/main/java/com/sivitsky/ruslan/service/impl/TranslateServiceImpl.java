package com.sivitsky.ruslan.service.impl;

import com.sivitsky.ruslan.exception.GeneralServiceException;
import com.sivitsky.ruslan.service.TranslateService;
import com.sivitsky.ruslan.service.response.TranslationResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    private String yandexTranslateApi;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Properties stringToProperties(String s) {
        try {
            Properties p = new Properties();
            p.load(new StringReader(s));
            return p;
        } catch (IOException e) {
            throw new GeneralServiceException("Invalid property format????", e);
        }
    }

    @Override
    public String propertiesToString(Properties p) {
        try {
            String s = "";
            Writer outWriter = new StringWriter();
            p.store(outWriter, s);
            return outWriter.toString();
        } catch (IOException e) {
            throw new GeneralServiceException("Invalid property format????", e);
        }
    }

    @Override
    public Properties translateProperties(String destinationLang, Properties p) {
        Properties properties = new Properties();
        Set keys = p.keySet();
        for (Object key : keys) {
            properties.put(key, translateTextInternal(destinationLang, p.getProperty(key.toString())));
        }
        return properties;
    }

    private String translateTextInternal(String targetLang, String originalText) {
        URI targetUrl = UriComponentsBuilder.fromUriString(yandexBaseUrl)
                .path(yandexTranslateApi)
                .queryParam("key", yandexKey)
                .queryParam("lang", targetLang)
                .queryParam("text", originalText)
                .build()
                .toUri();

        TranslationResponse response = restTemplate.getForObject(targetUrl, TranslationResponse.class);
        return response.getText().get(0);
    }
}
