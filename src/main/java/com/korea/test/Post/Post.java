package com.korea.test.Post;

import com.korea.test.NoteBook.Notebook;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;

    @ManyToOne
    private Notebook notebook;

    public void setNotebook(Long id) {
    }
}
