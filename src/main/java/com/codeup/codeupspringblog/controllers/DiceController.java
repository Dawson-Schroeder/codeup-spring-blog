package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String diceRoll(Model model){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollResult(@PathVariable int n, Model model){
        int randomNum = (int) Math.floor(Math.random() * 6 + 1);
        String message = "";
        if(n == randomNum){
            message = "Correct";
        } else {
            message = "Wrong";
        }

        model.addAttribute("guess", n);
        model.addAttribute("roll", randomNum);
        model.addAttribute("message", message);

        return "roll-dice";
    }
}
