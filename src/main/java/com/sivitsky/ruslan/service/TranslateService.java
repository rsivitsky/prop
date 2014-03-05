package com.sivitsky.ruslan.service;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Paul Jakimov
 *         Date: 1/28/14
 *         Time: 7:17 PM
 */
public interface TranslateService {

    Properties translateProperties(String destinationLanguage, Properties p);

    Properties stringToProperties(String s) throws IOException;

    String propertiesToString(Properties p) throws IOException;
}
