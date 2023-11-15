package com.korea.test.NoteBook;

import com.korea.test.Post.Post;
import com.korea.test.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotebookController {
    private final PostService postService;
    private final NotebookService notebookService;
    @RequestMapping("/notebook/{id}")
    public String main(Model model, @PathVariable("id") Long id) {
        //1. DB에서 데이터 꺼내오기
        List<Post> postList = this.postService.findAll();
        List<Notebook> notebookList = this.notebookService.findAll();

        //2. 꺼내온 데이터를 템플릿으로 보내기
        model.addAttribute("postList", postList);
        model.addAttribute("notebookList", notebookList);
        model.addAttribute("targetPost", postList.get(0));
        model.addAttribute("targetNotebook", notebookList.get(0));

        return "main";
    }
}
