package com.korea.test.Post;

import com.korea.test.NoteBook.Notebook;
import com.korea.test.NoteBook.NotebookService;
import jakarta.persistence.metamodel.IdentifiableType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final NotebookService notebookService;

    @RequestMapping("/test")
    @ResponseBody public String test() {
        return "test";
    }

    @RequestMapping("/")
    public String main(Model model) {
        //1. DB에서 데이터 꺼내오기
        List<Post> postList = this.postService.findAll();
        List<Notebook> notebookList = this.notebookService.findAll();

        if(notebookList.isEmpty()){
            this.notebookService.savedefault();
            return "redirect:/";
        }
        if(postList.isEmpty()){
            this.postService.savedefault();
            return "redirect:/";
        }

        //2. 꺼내온 데이터를 템플릿으로 보내기
        model.addAttribute("postList", postList);
        model.addAttribute("notebookList", notebookList);
        model.addAttribute("targetPost", postList.get(0));
        model.addAttribute("targetNotebook", notebookList.get(0));

        return "main";
    }

    @PostMapping("/write")
    public String write() {
        this.postService.savedefault();

        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        Post post = this.postService.findById(id);
        Long notebookId = post.getNotebook().getId();
        Notebook targetNotebook = this.notebookService.findById(notebookId);

        model.addAttribute("targetPost", post);
        model.addAttribute("targetNotebook", targetNotebook);
        model.addAttribute("postList", postService.findAll());
        model.addAttribute("notebookList", notebookService.findAll());

        return "main";
    }


    @PostMapping("/update")
    public String update(Long id, String title, String content) {
        Post post = this.postService.findById(id);
        if(title.trim().isEmpty()){
            title = "제목없음";
        }
        post.setTitle(title);
        post.setContent(content);

        this.postService.save(post);
        return "redirect:/detail/" + id;
    }

    @PostMapping("/delete")
    public String delete(Long id){
        Post post = this.postService.findById(id);
        this.postService.delete(post);
        return "redirect:/";
    }
}
