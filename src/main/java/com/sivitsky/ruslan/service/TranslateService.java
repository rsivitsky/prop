package com.sivitsky.ruslan.service;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Paul Jakimov
 *         Date: 1/28/14
 *         Time: 7:17 PM
 */
public interface TranslateService {

    Properties translateProp(String original_lang, String dest_lang, Properties p);

    Properties stringToProperties(String s) throws IOException;

    String translateLine(String src, String dest, String line);

    //String retrievesSourceToDest(String original_lang, String dest_lang, String original_text);

    //String v1(String original_lang, String dest_lang, String original_text);
}
