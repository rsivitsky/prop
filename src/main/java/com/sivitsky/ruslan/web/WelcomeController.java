package com.sivitsky.ruslan.web;

import com.sivitsky.ruslan.model.TranslateModel;
import com.sivitsky.ruslan.service.TranslateService;
import com.sivitsky.ruslan.service.impl.TranslateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * User: Tanya
 * Date: 09.01.14
 * Time: 1:27
 */
@Controller
public class WelcomeController {

    @Value("${yandex_base_url}")
    private String yandex_base_url;

    @Value("${yandex_key}")
    private String yandex_key;

    @Value("${translate_api}")
    private String translate_api;


    private final TranslateService translateService;


    @Autowired
    public WelcomeController(TranslateService translateService) {
        this.translateService = translateService;
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String initForm(ModelMap model) {

        TranslateModel translateModel = new TranslateModel();

        model.addAttribute("translateModel", translateModel);

        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String loginForumUser(@ModelAttribute("translateModel") TranslateModel translateModel) throws IOException, NoSuchFieldException {

        TranslateServiceImpl translateService = new TranslateServiceImpl();

        Properties properties = translateService.stringToProperties(translateModel.getSource());

        Properties properties1 = null;

        properties1 = translateService.translateProp(translateModel.getDest_langs(), properties);


        translateModel.setResult(translateService.propertiesToString(properties1));

        return "index";
    }

    @ModelAttribute("dest_langsList")
    public Map<String, String> langsList() {

        Map<String, String> dest_langs = new LinkedHashMap<String, String>();
        dest_langs.put("english", "en");
        dest_langs.put("albanian", "sq");
        dest_langs.put("armenian", "hy");
        dest_langs.put("azerbaijani", "az");

        dest_langs.put("byelorussian", "be");
        dest_langs.put("bulgarian", "bg");
        dest_langs.put("hungarian", "hu");
        dest_langs.put("flemish", "nl");

        dest_langs.put("greek", "el");
        dest_langs.put("danish", "da");
        dest_langs.put("italian", "it");
        dest_langs.put("spanish", "es");

        dest_langs.put("catalan", "ca");
        dest_langs.put("latvian", "lv");
        dest_langs.put("lithuanian", "lt");
        dest_langs.put("macedonian", "mk");

        dest_langs.put("german", "de");
        dest_langs.put("norwegian", "no");
        dest_langs.put("polish", "pl");
        dest_langs.put("portuguese", "pt");

        dest_langs.put("romanian", "ro");
        dest_langs.put("serbian", "sr");
        dest_langs.put("slovak", "sk");
        dest_langs.put("slovenian", "sl");

        dest_langs.put("turkish", "tr");
        dest_langs.put("ukrainian", "uk");
        dest_langs.put("french", "fr");
        dest_langs.put("croatian", "hr");

        dest_langs.put("czech", "cs");
        dest_langs.put("swedish", "sv");
        dest_langs.put("estonian", "et");
        dest_langs.put("russian", "ru");

        return dest_langs;
    }

}