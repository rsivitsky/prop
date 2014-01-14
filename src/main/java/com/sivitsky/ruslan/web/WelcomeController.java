package com.sivitsky.ruslan.web;

/**
 * Created with IntelliJ IDEA.
 * User: Tanya
 * Date: 09.01.14
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */

import com.sivitsky.ruslan.service.Translator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Properties;


@Controller
public class WelcomeController {
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView loginForumUser(@RequestParam("ishodnik") String ishodnik) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        Translator translator = new Translator();
        Properties rezult = translator.StringToProperties(ishodnik);
        Properties prop = translator.TranslateProp(rezult);
        String FinalString = translator.PropertiesToString(prop);
        modelAndView.addObject("rezult", FinalString);
        modelAndView.setViewName("index.jsp");

        return modelAndView;
    }

}