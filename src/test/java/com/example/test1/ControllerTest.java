package com.example.test1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSymbolsCounter() throws Exception {
        String input = "aaaaabcccc";

        mockMvc.perform(get("/symbols-counter")
                        .param("input", input))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a").value(5))
                .andExpect(jsonPath("$.c").value(4))
                .andExpect(jsonPath("$.b").value(1));
    }

    @Test
    void testBlankSymbols() throws Exception {
        String input = "";

        mockMvc.perform(get("/symbols-counter")
                        .param("input", input))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors").value( "must not be blank"));
    }

    @Test
    void testSymbolsCounterWithSpaces() throws Exception {
        String input = "aaaaa b ccc  dd";

        mockMvc.perform(get("/symbols-counter")
                        .param("input", input))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a").value(5))
                .andExpect(jsonPath("$.c").value(3))
                .andExpect(jsonPath("$.d").value(2))
                .andExpect(jsonPath("$.b").value(1));
    }

    @Test
    void testMissingParameter() throws Exception {
        mockMvc.perform(get("/symbols-counter"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors").value("input parameter is missing"));
    }

}