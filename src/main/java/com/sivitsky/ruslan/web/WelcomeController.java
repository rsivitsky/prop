package com.sivitsky.ruslan.web;

import com.sivitsky.ruslan.service.Translator;
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


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView loginForumUser(@RequestParam("source") String source) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        Translator translator = new Translator();
        Properties result = translator.stringToProperties(source);
        Properties prop = translator.translateProp(result);

        String finalString = translator.propertiesToString(prop);

        //String for_Print = translator.retrievesSourceToDest("en", "ru", "castle");
        //System.out.print(for_Print);
        modelAndView.addObject("source", source);
        modelAndView.addObject("result", finalString);
        modelAndView.setViewName("index");

        return modelAndView;
    }
}