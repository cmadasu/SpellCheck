package com.chiru.controller;

import com.chiru.WordRequest;
import com.chiru.service.SpellCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class SpellCheckController {
    @Autowired
    SpellCheckService spellCheckService;

    @CrossOrigin(origins = "*")
    @GetMapping("/word/new")
    public String getWord() {
        return spellCheckService.getNewWord();
    }

    @GetMapping("/word/validate")
    public String validateWord(@RequestBody WordRequest wordRequest, @RequestHeader(value = "client_id") String clientId) {
        if (null != wordRequest) {
            return spellCheckService.validate(wordRequest.getMaskedStr(), wordRequest.getUserStr(), clientId);
        }

        return "";
    }

}
