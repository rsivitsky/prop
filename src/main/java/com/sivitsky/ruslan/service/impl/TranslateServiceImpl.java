package com.sivitsky.ruslan.service.impl;

import com.sivitsky.ruslan.service.TranslateService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author Paul Jakimov
 *         Date: 1/28/14
 *         Time: 7:18 PM
 */
@Service
public class TranslateServiceImpl implements TranslateService {

    public static final String YANDEX_BASE_URL = "https://translate.yandex.net";
    public static final String YANDEX_KEY = "trnsl.1.1.20140211T084212Z.6d755fd3df9c1609.b4793690d02bea684c940bcf339050722c4757f8";

    @Override
    public String translateLine(String src, String dest, String line) {
        line = dest;
        return line.concat("!!!");
    }

    @Override
    public String retrievesSourceToDest(String original_lang, String dest_lang, String original_text) {
        URI targetUrl = UriComponentsBuilder.fromUriString(YANDEX_BASE_URL)
                .path("/api/v1.5/tr/translate")
                .queryParam("key", YANDEX_KEY)
                .queryParam("text", original_text)
                .queryParam("lang", original_lang + "-" + dest_lang)
                .build()
                .toUri();
        return new RestTemplate().getForObject(targetUrl, String.class);
    }

    public static void main(String[] args) {
        System.out.println(new TranslateServiceImpl().retrievesSourceToDest("en", "ru", "hello"));
    }
}
