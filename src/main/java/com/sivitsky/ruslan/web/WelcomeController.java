package com.sivitsky.ruslan.web;

import com.sivitsky.ruslan.model.TranslateModel;
import com.sivitsky.ruslan.service.TranslateService;
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

    @Autowired
    private TranslateService translateService;

    @Value("${yandex.base.url}")
    private String yandex_base_url;

    @Value("${yandex.key}")
    private String yandex_key;

    @Value("${yandex.translate.api}")
    private String translate_api;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String initForm(ModelMap model) {

        TranslateModel translateModel = new TranslateModel();

        model.addAttribute("translateModel", translateModel);

        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String loginForumUser(
            @ModelAttribute("translateModel") TranslateModel translateModel)
            throws IOException, NoSuchFieldException, ClassNotFoundException {

        Properties sourceProperties = translateService.stringToProperties(translateModel.getSource());
        Properties resultProperties = translateService.translateProperties(translateModel.getDest_langs(), sourceProperties);
        translateModel.setResult(translateService.propertiesToString(resultProperties));
        return "index";
    }

    @ModelAttribute("dest_langsList")
    public Map<String, String> getLanguages() {
        Map<String, String> result = new LinkedHashMap<String, String>();
        result.put("en", "english");
        result.put("sq", "albanian");
        result.put("hy", "armenian");
        result.put("az", "azerbaijani");
        result.put("be", "byelorussian");
        result.put("bg", "bulgarian");
        result.put("hu", "hungarian");
        result.put("nl", "flemish");
        result.put("el", "greek");
        result.put("da", "danish");
        result.put("it", "italian");
        result.put("es", "spanish");
        result.put("ca", "catalan");
        result.put("lv", "latvian");
        result.put("lt", "lithuanian");
        result.put("mk", "macedonian");
        result.put("de", "german");
        result.put("no", "norwegian");
        result.put("pl", "polish");
        result.put("pt", "portuguese");
        result.put("ro", "romanian");
        result.put("sr", "serbian");
        result.put("sk", "slovak");
        result.put("sl", "slovenian");
        result.put("tr", "turkish");
        result.put("uk", "ukrainian");
        result.put("fr", "french");
        result.put("hr", "croatian");
        result.put("cs", "czech");
        result.put("sv", "swedish");
        result.put("et", "estonian");
        result.put("ru", "russian");
        return result;
    }
}