package com.chiru.service;

import com.chiru.config.ConfigWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SpellCheckService {
    @Autowired
    ConfigWords configWords;
    HashMap<String, Integer> partialMatchMap = new HashMap<String, Integer>();

    public String getNewWord() {
        return configWords.getAnyWord();
    }

    public String validate(String inputStr, String userInput, String clientId) {
        String actualWord = configWords.getWord(inputStr);
        if (actualWord.equals(userInput)) return "Entered Word is correct: " + userInput;
        int matchedCharCount = checkForPartialMatch(actualWord, userInput);
        if (matchedCharCount > 0) {
            String key = clientId + "|" + inputStr;
            Integer value = partialMatchMap.get(key);
            if (null != value) {
                partialMatchMap.put(key, (Integer) partialMatchMap.get(clientId + "|" + inputStr) + 1);
                if (partialMatchMap.get(key).intValue() == 3) return "Actual answer is : " + actualWord;
            } else {
                partialMatchMap.put(key, 1);
            }
            return "Entered Word is incorrect. Try again ";
        }
        return "";
    }

    private int checkForPartialMatch(String actualWord, String userInput) {
        int cnt = 0;
        int i = 0;
        while (i < actualWord.length()) {
            if ((i % 2 == 1) && (actualWord.charAt(i) == userInput.charAt(i))) {
                cnt++;
            }
            i++;
        }
        return cnt;
    }

}
