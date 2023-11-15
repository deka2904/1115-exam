package com.korea.test.Post;

import com.korea.test.DataNotFoundException;
import com.korea.test.NoteBook.Notebook;
import com.korea.test.NoteBook.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final NotebookService notebookService;

    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    public void savedefault() {
        Post post = new Post();
        post.setTitle("New Title");
        post.setContent("");
        post.setCreateDate(LocalDateTime.now());
        postRepository.save(post);
    }


    public Post findById(Long id) {
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new DataNotFoundException("post not found");
        }
    }


    public void save(Post post) {
        this.postRepository.save(post);
    }

    public void delete(Post post) {
        this.postRepository.delete(post);
    }

    public List<Post> findByNotebookId(Long notebookId) {
        return postRepository.findByNotebookId(notebookId);
    }
}
