package com.sivitsky.ruslan.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.Set;

public class Translator {


    //
   /* private Properties props = new Properties();

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }  */
    public Properties TranslateProp(Properties p) {
        //пока тупо берем ключи пропертис и вставляем в пропертис якобы переведенное
        Properties properties = new Properties();
        Set keys;
        keys = p.keySet();
        for (Object key : keys) {
            properties.put(key, "");
        }
        return properties;
    }

    public String PropertiesToString(Properties p) throws IOException {
        String s = "";
        Writer outWriter = new StringWriter();
        p.store(outWriter, s);
        return outWriter.toString();
    }

    public Properties StringToProperties(String s) throws IOException {
        final Properties p = new Properties();
        p.load(new StringReader(s));
        return p;
    }
}
