package com.ForoHub.ForoAPI.controllers;

import com.ForoHub.ForoAPI.domain.posts.PostData;
import com.ForoHub.ForoAPI.domain.posts.PostResponse;
import com.ForoHub.ForoAPI.domain.posts.PostUpdateData;
import com.ForoHub.ForoAPI.services.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/posts")
@SecurityRequirement(name = "bearer-key")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping()
    public ResponseEntity<PostResponse> replyTopic(@RequestBody @Valid PostData postData, UriComponentsBuilder uriComponentsBuilder){
        PostResponse post = postService.createPost(postData);
        URI url = uriComponentsBuilder.path("/{id}").buildAndExpand(post.id()).toUri();
        return ResponseEntity.created(url).body(post);
    }

    @PutMapping()
    public ResponseEntity<PostResponse> updateReply(@RequestBody @Valid PostUpdateData postData){
        PostResponse post = postService.updatePost(postData);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/{topic}")
    public ResponseEntity<Page<PostResponse>> getAllReplies(@PathVariable String topic, @PageableDefault(size = 5) Pageable pagination){
        return ResponseEntity.ok(postService.getPostsByTopic(topic, pagination));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
