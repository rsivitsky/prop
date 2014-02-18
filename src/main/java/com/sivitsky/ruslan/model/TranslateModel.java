package com.sivitsky.ruslan.model;

/**
 * Created by Tanya on 19.02.14.
 */
public class TranslateModel {
    private String source;
    private String result;
    private String dest_langs;
    private String original_langs;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDest_langs() {
        return dest_langs;
    }

    public void setDest_langs(String dest_langs) {
        this.dest_langs = dest_langs;
    }

    public String getOriginal_langs() {
        return original_langs;
    }

    public void setOriginal_langs(String original_langs) {
        this.original_langs = original_langs;
    }
}
