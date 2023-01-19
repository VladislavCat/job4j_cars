package ru.job4j.cars.controller;

import ru.job4j.cars.util.UserName;
import lombok.RequiredArgsConstructor;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cars.service.CarService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class PostController implements PostControllerInterface {
    private final ru.job4j.cars.service.PostService postService;
    private final CarService carService;

    @GetMapping("/all_posts")
    public String all(Model model, HttpSession httpSession) {
        UserName.userSessionSetName(model, httpSession);
        model.addAttribute("posts", postService.findAll());
        return "/all_posts";
    }

    @GetMapping("/last_day_posts")
    public String lastDay(Model model, HttpSession httpSession) {
        UserName.userSessionSetName(model, httpSession);
        model.addAttribute("posts", postService.findLastDay());
        return "/last_day_posts";
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession httpSession) {
        UserName.userSessionSetName(model, httpSession);
        model.addAttribute("post", new Post());
        model.addAttribute("cars", carService.findAll());
        return "/create";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Post post, HttpSession httpSession,
                      @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            post.setPhoto(file.getBytes());
        }
        post.setCreated(LocalDateTime.now());
        post.setUser((User) httpSession.getAttribute("user"));
        post.setStatus(true);
        postService.addPost(post);
        return "redirect:/all_posts";
    }

    @GetMapping("/detail/{postId}")
    public String detail(@PathVariable("postId") int id, Model model, HttpSession httpSession) {
        UserName.userSessionSetName(model, httpSession);
        model.addAttribute("post", postService.findById(id));
        return "detail";
    }

    @GetMapping("/photoPost/{postId}")
    public ResponseEntity<Resource> download(@PathVariable("postId") Integer sessionId) {
        Post post = postService.findById(sessionId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(post.getPhoto().length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(post.getPhoto()));
    }
}
