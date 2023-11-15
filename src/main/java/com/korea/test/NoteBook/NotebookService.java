package com.korea.test.NoteBook;

import com.korea.test.DataNotFoundException;
import com.korea.test.Post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotebookService {
    private final NotebookRepository notebookRepository;

    public List<Notebook> findAll() {
        return this.notebookRepository.findAll();
    }

    public void savedefault() {
        Notebook notebook = new Notebook();
        notebook.setContent("새노트");
        notebook.setCreateDate(LocalDateTime.now());

        notebookRepository.save(notebook);
    }

    public Notebook findById(Long notebookId) {
        Optional<Notebook> notebook = this.notebookRepository.findById(notebookId);
        if (notebook.isPresent()) {
            return notebook.get();
        } else {
            throw new DataNotFoundException("notebook not found");
        }
    }
}
