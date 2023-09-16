package com.example.test1;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("/")
@Validated
public class Controller {

    @GetMapping(value = "/symbols-counter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Character, Integer> symbolsCounter(@RequestParam @NotBlank String input){
        String reduce = Arrays.stream(input.split("\\s+")).reduce(String::concat).get();

        Map<Character, Integer> counterMap = new HashMap<>();
        for (char aChar : reduce.toCharArray()) {
            counterMap.put(aChar, counterMap.getOrDefault(aChar, 0) + 1);
        }

        return counterMap.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
