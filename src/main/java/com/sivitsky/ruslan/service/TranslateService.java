package com.sivitsky.ruslan.service;

/**
 * @author Paul Jakimov
 *         Date: 1/28/14
 *         Time: 7:17 PM
 */
public interface TranslateService {

    String translateLine(String src, String dest, String line);
}
