package com.sivitsky.ruslan.web;

import com.sivitsky.ruslan.service.TranslateService;
import com.sivitsky.ruslan.service.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;

/**
 * User: Tanya
 * Date: 09.01.14
 * Time: 1:27
 */
@Controller
public class WelcomeController {

    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);

    @Resource
    private TranslateService translateService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView loginForumUser(@RequestParam("source") String source) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        Translator translator = new Translator();
        Properties sourceProps = translator.stringToProperties(source);

        for (String name : sourceProps.stringPropertyNames()) {
            //Вот тут вот показано как работает сервис. Смотри в логи.
            LOG.info("Translating: {}", name);
            LOG.info("\nsrc: {}\ntgt: {}",
                    sourceProps.getProperty(name),
                    translateService.translateLine("en", "ru", sourceProps.getProperty(name)));
        }

        ///////////////////////////////////////////////////////////////////////////////////////
        // Все что под этой линией - я не трогал. Надо сделать из этого приложение
        // А потом разберешься с сервисом

        Properties prop = translator.translateProp(sourceProps);
        String finalString = translator.propertiesToString(prop);
        String for_Print = translator.retrievesSourceToDest("en", "ru", "castle");
        System.out.print(for_Print);
        modelAndView.addObject("source", source);
        modelAndView.addObject("result", finalString);
        modelAndView.setViewName("index");

        return modelAndView;
    }
}