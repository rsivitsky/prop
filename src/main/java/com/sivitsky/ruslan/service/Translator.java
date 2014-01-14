package com.sivitsky.ruslan.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
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
        String str;
        keys = p.keySet();
        Iterator itr = keys.iterator();
        while (itr.hasNext()) {
            properties.put((String) itr.next(), "");
        }
        return properties;
    }

    public String PropertiesToString(Properties p) throws IOException {
        String s = new String();
        Writer outWriter = new StringWriter();
        p.store(outWriter, s);
        String finalstring = outWriter.toString();
        return finalstring;
    }

    public Properties StringToProperties(String s) throws IOException {
        final Properties p = new Properties();
        p.load(new StringReader(s));
        return p;
    }
}
