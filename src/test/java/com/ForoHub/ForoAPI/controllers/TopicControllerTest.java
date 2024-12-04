package com.ForoHub.ForoAPI.controllers;

import com.ForoHub.ForoAPI.domain.Status;
import com.ForoHub.ForoAPI.domain.topic.TopicData;
import com.ForoHub.ForoAPI.domain.topic.TopicResponse;
import com.ForoHub.ForoAPI.services.TopicService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import static org.mockito.ArgumentMatchers.any;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest//load all setting of springboot(Integration Test)
@AutoConfigureMockMvc//setting integration test with MOCKS
@AutoConfigureJsonTesters//serilization and desserialization of JSON.
class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;//(send request without start server)

    @Autowired
    private JacksonTester<TopicData> topicJson;

    @Autowired
    private JacksonTester<TopicResponse> topicResponseJson;

    @Autowired
    private JacksonTester<Page<TopicResponse>> pageJson;

    @MockBean // replace a Bean componente for MockBean
    private TopicService topicService;

    @Test
    @DisplayName("Must be returned HTTP 440 when request body is empty")
    @WithMockUser//simulated authenticated user
    void createNewTopicWithoutBody() throws Exception {
        var response = mockMvc.perform(post("/api/topics"))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Must be return HTTP 200 when topic object was created")
    @WithMockUser
    void createNewTopicSucessfully() throws Exception {
        var topic = new TopicData("weather", "it's cold","anonymo", "stations");
        var expectedTopic = new TopicResponse(1L,"weather", "it's cold", "anonymo", "stations", Status.ACTIVE, LocalDate.now());

        when(topicService.createTopic(any(TopicData.class))).thenReturn(expectedTopic);
        var response = mockMvc.perform(post("/api/topics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(topicJson.write(
                                topic
                        ).getJson())
                )
                .andReturn()
                .getResponse();
        var expectedJson = topicResponseJson.write(expectedTopic).getJson();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(expectedJson, response.getContentAsString());
    }

    @Test
    @DisplayName("Must be return HTTP 200 when topics are sending")
    @WithMockUser
    void getAllTopics() throws Exception {
        var topics = List.of(
                new TopicResponse(1L, "weather", "it's cold", "anonymo", "stations", Status.ACTIVE, LocalDate.now()),
                new TopicResponse(2L, "technology", "AI trends", "user123", "innovation", Status.ACTIVE, LocalDate.now())
        );
        //create page 0, with 2 elements(size)
        var pageable = PageRequest.of(0, 3);
        var page = new PageImpl<>(topics, pageable, topics.size());

        when(topicService.getTopics(any(Pageable.class))).thenReturn(page);

        var response = mockMvc.perform(get("/api/topics?page=0&size=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        var expectedJson = pageJson.write(page).getJson();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(expectedJson, response.getContentAsString(), true);
    }

    @Test
    @DisplayName("Return HTTP 200 with correct response")
    @WithMockUser
    void getTopicById() throws Exception {
        TopicResponse topic = new TopicResponse(1L, "weather", "it's cold", "anonymo", "stations", Status.ACTIVE, LocalDate.now());

        when(topicService.searchTopicBy(any(Long.class))).thenReturn(topic);
        var response = mockMvc.perform(get("/api/topics/1").contentType(
                MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var expectedJson = topicResponseJson.write(topic).getJson();
        assertEquals(expectedJson, response.getContentAsString());
    }

    @Test
    @DisplayName("Return HTTP 404 when the topic was not found")
    @WithMockUser
    void getTopicByIdNotFound() throws Exception {
        when(topicService.searchTopicBy(any(Long.class)))
                .thenThrow(new EntityNotFoundException("Topic not Found"));
        var response = mockMvc.perform(get("/api/topics/1").contentType(
                        MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Topic not Found"));
    }

    @Test
    @DisplayName("Return HTTP 200 with topic json updated")
    @WithMockUser
    void updateTopic() {

    }

    @Test
    @DisplayName("Nothing is return when topic is deleted successfully")
    @WithMockUser
    void deleteTopic() throws Exception {
        doNothing().when(topicService).deleteTopic(any(Long.class));
        mockMvc.perform(delete("/api/topics/1")
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
        verify(topicService, times(1)).deleteTopic(eq(1L));
    }

    @Test
    @DisplayName("Must return HTTP 404 when topic is deleted successfully")
    @WithMockUser
    void deleteTopicNotFound() throws Exception {
        doThrow(new EntityNotFoundException("Topic was not Found"))
            .when(topicService).deleteTopic(any(Long.class));
        mockMvc.perform(delete("/api/topics/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Entity Not Found"))
                .andExpect(jsonPath("$.message").value("Topic was not Found"));
    }
}