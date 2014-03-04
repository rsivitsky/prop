package com.sivitsky.ruslan.web;

import com.sivitsky.ruslan.service.TranslateService;
import com.sivitsky.ruslan.web.model.TranslateModel;
import org.springframework.beans.factory.annotation.Autowired;
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

        Properties source = translateService.stringToProperties(translateModel.getSource());
        Properties result = translateService.translateProperties(translateModel.getDestinationLanguage(), source);
        translateModel.setResult(translateService.propertiesToString(result));
        return "index";
    }

    @ModelAttribute("supportedLanguages")
    public Map<String, String> getSupportedLanguages() {
        Map<String, String> result = new LinkedHashMap<>();
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