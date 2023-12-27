package com.redpoints.interview.controller;


import com.redpoints.interview.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
class MovieControllerTest {

    // For integration test another H2-database was used, separated from one used in development
    // Also this class was renamed (MovieControllerTestIT -> MovieControllerTest) to follow test
    // naming convention, and thus being able to run this test with "mvn test"

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getMovieByIdEndpointPositive() throws Exception {


        mockMvc.perform(get("/movies/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Oppenheimer")));
    }


    @Test
    void getMovieByIdEndpointNegative_InvalidId() throws Exception {

        mockMvc.perform(get("/movies/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    void getMovieByIdEndpointNegative_NullId() throws Exception {

        mockMvc.perform(get("/movies/null")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}