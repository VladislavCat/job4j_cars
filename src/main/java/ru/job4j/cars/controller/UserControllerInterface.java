package ru.job4j.cars.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserControllerInterface {
    String logout(HttpSession httpSession);
    String profile(Model model, HttpSession httpSession);
    String registration(Model model, @ModelAttribute User user);
    String createUser(Model model, @RequestParam(name = "fail", required = false) Boolean fail);
    String login(@ModelAttribute User user, HttpServletRequest req);
    String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail);
}
