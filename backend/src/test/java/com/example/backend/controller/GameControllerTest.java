package com.example.backend.controller;

import com.example.backend.model.Game;
import com.example.backend.repository.GameRepository;
import com.example.backend.service.IdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;

    @MockBean
    private IdService idService;

    @DirtiesContext
    @Test
    void addName_shouldReturn_newMeeting() throws Exception {
        //GIVEN
        when(idService.generateID()).thenReturn("123");

        gameRepository.save(new Game("123",null,null,null,null, new String[]{"Klaus", "Lisa"}));

        String expectedJson = """
                {
                "gameId":"123",
                "currentQuestionId":null,
                "round":null,
                "maxRounds":null,
                "currentPlayer":null,
                "players":["Klaus", "Lisa"]
                }
                """;

        //WHEN&THEN
        mockMvc.perform(post("/api/game")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content("""
                        {"players"
                            :[
                                "Klaus",
                                "Lisa"
                            ]
                         }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @DirtiesContext
    @Test
    void getGameById_shouldReturn_oneGameById() throws Exception {
        //GIVEN
        gameRepository.save(new Game("123",null,null,null,null, new String[]{"Klaus", "Lisa"}));

        String expectedJson = """
                {
                "gameId":"123",
                "currentQuestionId":null,
                "round":null,
                "maxRounds":null,
                "currentPlayer":null,
                "players":["Klaus", "Lisa"]
                }
                """;

        //WHEN&THEN
        this.mockMvc.perform(get("/api/game/123"))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedJson));
    }

    @DirtiesContext
    @Test
    void deleteGameById_shouldDeleteGame() throws Exception {
        //GIVEN
        gameRepository.save(new Game("123",null,null,null,null, new String[]{"Klaus", "Lisa"}));
        //WHEN&THEN
        this.mockMvc.perform(delete("/api/game/123"))
                .andExpect(status().isOk());
    }
}