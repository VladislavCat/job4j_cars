package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cars.service.UserService;
import ru.job4j.cars.util.UserName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController implements UserControllerInterface {
    private final UserService userService;

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "/login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userDb = userService.findUserByUsernameAndPassword(
                user.getUsername(), user.getPassword()
        );
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/all_posts";
    }

    @GetMapping("/formRegistration")
    public String createUser(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("user", new User("username", "password"));
        model.addAttribute("fail", fail != null);
        return "formRegistration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
        Optional<User> regUser = Optional.ofNullable(userService.add(user));
        if (regUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с таким именем уже существует");
            return "redirect:/formRegistration?fail=true";
        }
        return "redirect:/all_tasks";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login_page";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        UserName.userSessionSetName(model, session);
        return "profile";
    }
}
