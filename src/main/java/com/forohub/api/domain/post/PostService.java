package com.forohub.api.domain.post;

import com.forohub.api.domain.ValidationException;
import com.forohub.api.domain.course.CourseRepository;
import com.forohub.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PostRepository postRepository;

    public DataPost addNewPost(DataPost dataPost) {
        if(!userRepository.existsById(dataPost.idUser())){
            throw new ValidationException("No existe un usuario con ese id");
        }
        if(!courseRepository.existsById(dataPost.idCourse())){
            throw new ValidationException("No existe una course con ese id");
        }

        Optional<Post> existingPost = postRepository.findByTitleAndContent(dataPost.title(), dataPost.content());

        if (existingPost.isPresent()) {
            throw new ValidationException("Ya existe un post con el mismo título y subject.");
        }

        var user = userRepository.findById(dataPost.idUser()).get();
        var course = courseRepository.findById(dataPost.idCourse()).get();
        var date = LocalDateTime.now();
        var post = new Post(null, dataPost.title(), dataPost.content(), TRUE, date, user, course);
        postRepository.save(post);
        return dataPost;
    }
}
