package com.sivitsky.ruslan.web;

import com.sivitsky.ruslan.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Properties;

/**
 * User: Tanya
 * Date: 09.01.14
 * Time: 1:27
 */
@Controller
public class WelcomeController {


    private final TranslateService translateService;


    @Autowired
    public WelcomeController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView loginForumUser(@RequestParam("source") String source, @RequestParam("dest_langs") String dest_langs) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        //Translator translator = new Translator();
        // TranslateService translateService1;
        //Properties result = translator.stringToProperties(source);
        //Properties prop = translator.translateProp(result);

        //String finalString = translator.propertiesToString(prop);

        //TranslateServiceImpl translateService = new TranslateServiceImpl();
        //finalString = translateService.translateLine(source, finalString, "");

        //String finalString = translateService.retrievesSourceToDest(original_langs, dest_langs, source);
        // String finalString = translateService.v1(original_langs, dest_langs, source);

        //TranslateServiceImpl translateService1 = new TranslateServiceImpl();

        Properties properties = translateService.stringToProperties(source);

        Properties properties1 = translateService.translateProp(dest_langs, properties);

        String result = translateService.propertiesToString(properties1);


        modelAndView.addObject("source", source);
        modelAndView.addObject("result", result);
        modelAndView.setViewName("index");

        return modelAndView;
    }
}