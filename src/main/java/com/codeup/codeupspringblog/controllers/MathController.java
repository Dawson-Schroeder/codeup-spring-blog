package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @GetMapping("/math")
    @ResponseBody
    public String welcome(){
        return "Welcome to my math page";
    }
    @GetMapping("/math/add/{var1}/and/{var2}")
    @ResponseBody
    public String add(@PathVariable int var1, @PathVariable int var2){
        return String.valueOf(var1 + var2);
    }

    @GetMapping("/math/subtract/{var1}/and/{var2}")
    @ResponseBody
    public String subtract(@PathVariable int var1, @PathVariable int var2){
        return String.valueOf(var2 - var1);
    }

    @GetMapping("/math/multiply/{var1}/and/{var2}")
    @ResponseBody
    public String multiply(@PathVariable int var1, @PathVariable int var2){
        return String.valueOf(var1 * var2);
    }
    @GetMapping("/math/divide/{var1}/by/{var2}")
    @ResponseBody
    public String divide(@PathVariable int var1, @PathVariable int var2){
        return String.valueOf(var1 / var2);
    }



}
