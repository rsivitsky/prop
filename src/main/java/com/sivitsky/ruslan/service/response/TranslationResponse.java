package com.sivitsky.ruslan.service.response;

/**
 * @author Paul Jakimov
 *         Date: 3/4/14
 *         Time: 8:04 PM
 */
public class TranslationResponse {

    private int code;
    private String lang;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
