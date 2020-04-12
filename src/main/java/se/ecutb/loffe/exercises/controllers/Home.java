package se.ecutb.loffe.exercises.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Home {

    List<String> stringList = new ArrayList<>();

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String contact(@RequestParam String input) {
        if (!input.isEmpty()) stringList.add(input);
        return "redirect:/contact";
    }

    @GetMapping("/contact_list")
    public String contactList(Model model) {
        model.addAttribute("contacts", stringList);
        return "contact_list";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }


    @GetMapping("/fever_controller")
    public String feverController() {
        return "fever-controller";
    }

    @PostMapping("/fever_controller")
    public String temp(@RequestParam(name = "temp") Double temp, Model model){

        if(temp <= 35) {
            model.addAttribute("text", "Since your body temperature is " + temp + "°C you suffer from hypothermia.");
            model.addAttribute("text2", "Please find more info at <a href=\"https://sv.wikipedia.org/wiki/Hypotermi\">Wikipedia - Hypotermi</a>.");
            return "low";
        }

        if(temp > 35 && temp < 38) {
            model.addAttribute("text", "Since your body temperature is " + temp + "°C you have a normal body temperature.");
            return "normal";
        }

        if(temp >= 38){
            model.addAttribute("text", "Since your body temperature is " + temp + "°C you suffer from fever or hyperthermia.");
            model.addAttribute("text2", "Please find more info at <a href=\"https://sv.wikipedia.org/wiki/Hypertermi\">Wikipedia - Hypertermi</a>,\n" +
                    "    or <a href=\"https://sv.wikipedia.org/wiki/Feber\">Wikipedia - feber</a>.");
            return "high";

        }
        return "fever-controller";
    }
}
