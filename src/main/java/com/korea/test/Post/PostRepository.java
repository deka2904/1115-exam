package com.korea.test.Post;

import com.korea.test.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>  {
    List<Post> findByNotebookId(Long notebookId);
}
