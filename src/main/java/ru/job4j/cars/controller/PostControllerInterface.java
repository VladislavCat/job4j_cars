package ru.job4j.cars.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Post;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface PostControllerInterface {
    String all(Model model, HttpSession httpSession);
    String lastDay(Model model, HttpSession httpSession);
    String create(Model model, HttpSession httpSession);
    String add(@ModelAttribute Post post, HttpSession httpSession,
               @RequestParam("file") MultipartFile file) throws IOException;
    String detail(@PathVariable("postId") int id, Model model, HttpSession httpSession);
    ResponseEntity<Resource> download(@PathVariable("postId") Integer sessionId);
}
