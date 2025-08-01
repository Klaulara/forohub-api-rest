package com.forohub.api.controller;

import com.forohub.api.domain.post.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PagedResourcesAssembler<DataListPost> pagedAssembler;

    @Autowired
    private DataListPostModelAssembler dataListPostModelAssembler;

    @PostMapping
    @Transactional
    public ResponseEntity registerPost(@RequestBody @Valid DataPost dataPost) {

        var data = postService.addNewPost(dataPost);

        return ResponseEntity.ok().body(data);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DataListPost>>> getAllPosts(@PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DataListPost> page = postRepository.findAll(pageable).map(DataListPost::new);
        var response = pagedAssembler.toModel(page, dataListPostModelAssembler);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity findPostById(@PathVariable String id) {
        var post = postRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataListPost(post));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updatePost(@PathVariable String id, @RequestBody @Valid DataEditPost dataPost) {
        var post = postRepository.getReferenceById(id);
        post.updatePost(dataPost);
        return ResponseEntity.ok(new DataListPost(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePost(@PathVariable String id) {
        var post = postRepository.getReferenceById(id);
        post.softDeletePost();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/hardDelete/{id}")
    @Transactional
    public ResponseEntity hardDeletePost(@PathVariable String id) {
        postRepository.getReferenceById(id);
        postRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
