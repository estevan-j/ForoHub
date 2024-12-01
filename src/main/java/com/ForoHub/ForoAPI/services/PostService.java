package com.ForoHub.ForoAPI.services;

import com.ForoHub.ForoAPI.domain.posts.Post;
import com.ForoHub.ForoAPI.domain.posts.PostData;
import com.ForoHub.ForoAPI.domain.posts.PostResponse;
import com.ForoHub.ForoAPI.domain.posts.PostUpdateData;
import com.ForoHub.ForoAPI.infra.errors.CustomValidationsErrors;
import com.ForoHub.ForoAPI.repositories.PostRepository;
import com.ForoHub.ForoAPI.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicRepository;


    @SneakyThrows
    public PostResponse createPost(PostData postData)  {
        if (!topicRepository.existsByTitle(postData.topic())){
            throw new CustomValidationsErrors("That's topic is not registered :"+postData.topic());
        }
        var topic = topicRepository.findByTitle(postData.topic());
        Post newPost = postRepository.save(new Post(postData, topic));
        return convertPostToDTO(newPost);
    }



    private PostResponse convertPostToDTO(Post post) {
        return new PostResponse(post.getId(), post.getMessage(), post.getCreatedAt(), post.getAuthor(), post.getTopic().getTitle());
    }

    @SneakyThrows
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException("There's no posts with that id");
        }
        postRepository.deleteById(id);
    }

    @Transactional
    public PostResponse updatePost(PostUpdateData postData) {
        Post current = postRepository.getReferenceById(postData.id());
        current.updatePost(postData);
        return convertPostToDTO(current);
    }

    public Page<PostResponse> getPostsByTopic(String topic, Pageable pagination) {
        return postRepository.findAllByTitle(topic, pagination).map(post -> convertPostToDTO(post));
    }
}
