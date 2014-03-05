package com.sivitsky.ruslan.service.response;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul Jakimov
 *         Date: 3/4/14
 *         Time: 8:04 PM
 */
public class TranslationResponse {

    @JsonProperty("code")
    private int code;

    @JsonProperty("lang")
    private String lang;

    @JsonProperty("text")
    private List<String> text = new ArrayList<>();

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

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
