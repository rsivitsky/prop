package com.sivitsky.ruslan.service.impl;

import com.sivitsky.ruslan.service.TranslateService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

/**
 * @author Paul Jakimov
 *         Date: 1/28/14
 *         Time: 7:18 PM
 */
@Service
public class TranslateServiceImpl implements TranslateService {

    public static final String YANDEX_BASE_URL = "https://translate.yandex.net";
    public static final String YANDEX_KEY = "trnsl.1.1.20140211T084212Z.6d755fd3df9c1609.b4793690d02bea684c940bcf339050722c4757f8";
    public static final String YANDEX_V2_URL = "https://translate.yandex.net/api/v1.5/tr/translate?key={key}&lang={lang}&text={text}";
    public static final String TRANSLATE_API = "/api/v1.5/tr/translate";

    @Override
    public String translateLine(String src, String dest, String line) {
        line = dest;
        return line.concat("!!!");
    }

    @Override
    public String retrievesSourceToDest(String original_lang, String dest_lang, String original_text) {
        return v1(original_lang, dest_lang, original_text);
    }

    @Override
    public String v1(String original_lang, String dest_lang, String original_text) {
        URI targetUrl = UriComponentsBuilder.fromUriString(YANDEX_BASE_URL)
                .path(TRANSLATE_API)
                .queryParam("key", YANDEX_KEY)
                .queryParam("lang", original_lang + "-" + dest_lang)
                .queryParam("text", original_text)
                .build()
                .toUri();
        return new RestTemplate().getForObject(targetUrl, String.class);
    }

    private String v2(final String original_lang, final String dest_lang, final String original_text) {
        return new RestTemplate().getForObject(YANDEX_V2_URL, String.class, new HashMap<String, String>() {{
            put("key", YANDEX_KEY);
            put("lang", original_lang + "-" + dest_lang);
            put("text", original_text);
        }});
    }

    public static void main(String[] args) {
        System.out.println(new TranslateServiceImpl().v1("en", "ru", "hello"));
        System.out.println(new TranslateServiceImpl().v2("en", "ru", "hello"));
    }
}
