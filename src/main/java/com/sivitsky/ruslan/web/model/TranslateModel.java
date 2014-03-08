package com.sivitsky.ruslan.web.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Tanya
 *         on 19.02.14.
 */
public class TranslateModel {

    @NotNull
    @Pattern(regexp = "[A-Za-z_-]+=[A-Za-zА-Яа-я0-9.-]",
            message = "Invalid email address.")
    private String source;
    private String result;
    @NotNull
    private String destinationLanguage;
    private String originalLanguage;

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

    public String getDestinationLanguage() {
        return destinationLanguage;
    }

    public void setDestinationLanguage(String destinationLanguage) {
        this.destinationLanguage = destinationLanguage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
}
