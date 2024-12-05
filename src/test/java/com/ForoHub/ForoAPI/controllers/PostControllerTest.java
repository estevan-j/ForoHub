package com.ForoHub.ForoAPI.controllers;

import com.ForoHub.ForoAPI.domain.posts.PostData;
import com.ForoHub.ForoAPI.domain.posts.PostResponse;
import com.ForoHub.ForoAPI.domain.posts.PostUpdateData;
import com.ForoHub.ForoAPI.services.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<PostData> postJson;

    @Autowired
    private JacksonTester<PostResponse> postResponseJson;

    @Autowired
    private JacksonTester<PostUpdateData> postUpdateJson;

    @MockBean
    private PostService postService;

    @Test
    @DisplayName("Return 403 when the post wast not created")
    @WithMockUser
    void norReplyTopic() throws Exception {
        var response = mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Return HTTP 201  when the post wast created sucessfully")
    @WithMockUser
    void replyTopic() throws Exception {
        PostData newPost = new PostData("this is a test, message", null, "test1", "bod");
        PostResponse postResponse = new PostResponse(null,newPost.message(), LocalDateTime.now(),newPost.author(), newPost.topic());
        when(postService.createPost(any(PostData.class))).thenReturn(postResponse);
        var response = mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON)
                        .content(postJson.write(newPost).getJson()))
                .andReturn()
                .getResponse();

        var expectedJson = postResponseJson.write(postResponse).getJson();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(expectedJson, response.getContentAsString());
    }

    @Test
    @DisplayName("Return HTTP 403  when the post has bad structure")
    @WithMockUser
    void replyTopicWithBadJson() throws Exception {
        PostData newPost = new PostData("this is a test, message", null, "", "bod");
        var response = mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON)
                        .content(postJson.write(newPost).getJson()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("topic: must not be blank. "))
                .andExpect(jsonPath("$.error").value("Invalid JSON"));
    }

    @Test
    @DisplayName("Return HTTP 404 when the post was not found")
    @WithMockUser
    void updateReplyNotFound() throws Exception {
        PostUpdateData newData = new PostUpdateData(1L, "this is a new message");
        when(postService.updatePost(any(PostUpdateData.class))).thenThrow(new EntityNotFoundException("The post was not found"));
        var response = mockMvc.perform(put("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postUpdateJson.write(newData).getJson()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("The post was not found"))
                .andReturn()
                .getResponse();
    }

    @Test
    @DisplayName("Return HTTP 200 when the post was updated")
    @WithMockUser
    void updateReply() throws Exception {
        PostUpdateData newData = new PostUpdateData(1L, "this is a new message");
        PostResponse post = new PostResponse(1L,newData.message(), null, "test1", "bod");
        when(postService.updatePost(any(PostUpdateData.class))).thenReturn(post);
        var response = mockMvc.perform(put("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postUpdateJson.write(newData).getJson()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var expectedJson = postResponseJson.write(post).getJson();
        assertEquals(expectedJson, response.getContentAsString());
    }

    @Test
    void getAllReplies() {
    }

    @Test
    @DisplayName("Must be return HTTP 204 when the post was deleted ")
    @WithMockUser
    void deletePost() throws Exception {
        doNothing().when(postService).deletePost(anyLong());
        mockMvc.perform(delete("/api/posts/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(postService, times(1)).deletePost(eq(1L));
    }

    @Test
    @DisplayName("Must be return HTTP 404 when the post was not found")
    @WithMockUser
    void deletePostNotFound() throws Exception {
        doThrow(new EntityNotFoundException("The post was not found")).when(postService).deletePost(anyLong());
        mockMvc.perform(delete("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Entity Not Found"))
                .andExpect(jsonPath("$.message").value("The post was not found"));
    }
}