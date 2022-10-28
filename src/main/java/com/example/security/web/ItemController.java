package com.example.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
public class ItemController {

    @GetMapping(value = "/board")
    public String itemForm() {
        return "item/itemForm";
    }
}
