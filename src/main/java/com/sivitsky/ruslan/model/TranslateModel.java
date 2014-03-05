package com.sivitsky.ruslan.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Tanya on 19.02.14.
 */


public class TranslateModel {


    @NotNull
    @Pattern(regexp = "[A-Za-z0-9._%+-]+=[A-Za-zА-Яа-я0-9._%+-]", message = "invalid properties strings")
    private String source;

    private String result;


    @Pattern(regexp = "[a-z]", message = "invalid destination language value")
    @Size(min = 2, max = 22)
    @NotNull
    private String dest_langs;


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

}
