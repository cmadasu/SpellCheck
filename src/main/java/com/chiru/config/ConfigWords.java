package com.chiru.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Log4j2
@Configuration
public class ConfigWords {
    @Value("${words}")
    String words;
    private HashMap<String, String> wordMap;
    private List<String> wordsList = new ArrayList<>();


    public String getWord(String inputStr) {
        return wordMap.get(inputStr);
    }

    public String getAnyWord() {
        log.info("getAnyWord start");
        if ((null != words) && (null == wordMap)) {
            String[] wordsLst = words.split(",");
            wordMap = new HashMap<>();
            for (String word : wordsLst) {
                if (word.trim() != "") {
                    wordMap.put(maskStr(word), word);
                    wordsList.add(word);
                }
            }
        }
        Random random = new Random();
        log.info("getAnyWord end");
        return maskStr(wordsList.get(random.nextInt((wordsList.size() - 1))));
    }

    private String maskStr(String iStr) {
        log.info("maskStr start" + iStr);
        int i = 0;
        StringBuilder maskedWordBuilder = new StringBuilder(iStr);
        while (i < iStr.length()) {
            if (i % 2 == 1) {
                maskedWordBuilder.setCharAt(i, '*');
            }
            i++;
        }
        log.info("maskStr end");
        return maskedWordBuilder.toString();
    }
}
