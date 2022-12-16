package com.service.impl;

import org.springframework.stereotype.Service;
import com.service.IDictionaryService;

import java.util.HashMap;
import java.util.Map;

@Service
public class DictionaryService implements IDictionaryService {
    public static Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("watermelon", "dưa hấu");
        dictionary.put("orange", "cam");
        dictionary.put("apple", "táo");
        dictionary.put("mango", "xoài");
    }


    @Override
    public String lookUp(String word) {
        String wordFound = "Not found!";

        for (String x : dictionary.keySet()) {
            if (x.equals(word)) {
                wordFound = dictionary.get(x);
                return wordFound;
            }
        }

        return wordFound;
    }
}
