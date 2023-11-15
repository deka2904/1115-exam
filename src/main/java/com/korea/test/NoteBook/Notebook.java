package com.korea.test.NoteBook;

import com.korea.test.Post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "notebook")
    private List<Post> postList;
}
