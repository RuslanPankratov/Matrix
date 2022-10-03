package com.example.matrix.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MatrixControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void mirrorIT() throws Exception {
        MvcResult result = mockMvc.perform(post("/matrix/mirror")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("input/test_input.json")))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(readJson("output/expected_output.json"), content, true);
    }


    @Test
    void mirrorDiagonalIT() throws Exception {
        MvcResult result = mockMvc.perform(post("/matrix/mirror/diagonal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("input/test_input.json")))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(readJson("output/expected_diagonal_output.json"), content, true);
    }

    private String readJson(String path) {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            return mapper.writeValueAsString(jsonNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}