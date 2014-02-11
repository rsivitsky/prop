package com.sivitsky.ruslan.service.impl;

import com.sivitsky.ruslan.service.TranslateService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Paul Jakimov
 *         Date: 1/28/14
 *         Time: 7:18 PM
 */
@Service
public class TranslateServiceImpl implements TranslateService {

    @Override
    public String translateLine(String src, String dest, String line) {
        line = dest;
        return line.concat("!!!");
    }

    @Override
    public String retrievesSourceToDest(String original_lang, String dest_lang, String original_text) {
        Map<String, String> params = new HashMap<>();

        params.put("key", "trnsl.1.1.20140211T084212Z.6d755fd3df9c1609.b4793690d02bea684c940bcf339050722c4757f8");
        // params.put("lang", original_lang + "-" + dest_lang);

        params.put("text", original_text);
        params.put("lang", dest_lang);
        // params.put("format", "plain");
        // params.put("options", "1");
        return new RestTemplate().getForObject("https://translate.yandex.net/api/v1.5/tr/translate", String.class, params);
    }
}
