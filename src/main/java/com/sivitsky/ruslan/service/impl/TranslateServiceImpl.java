package com.sivitsky.ruslan.service.impl;

import com.sivitsky.ruslan.service.TranslateService;
import org.springframework.stereotype.Service;

/**
 * @author Paul Jakimov
 *         Date: 1/28/14
 *         Time: 7:18 PM
 */
@Service
public class TranslateServiceImpl implements TranslateService {

    @Override
    public String translateLine(String src, String dest, String line) {
        return line.concat("!!!");
    }
}
