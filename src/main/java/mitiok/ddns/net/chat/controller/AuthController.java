package mitiok.ddns.net.chat.controller;

import mitiok.ddns.net.chat.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/chat")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public String showLoginForm() {
    return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam String username,
                      @RequestParam String password,
                      HttpSession session,
                      Model model) {
    if (userService.checkLogin(username, password)) {
      session.setAttribute("username", username);
      // Редиректим сразу в чат
      return "redirect:/chatroom";
    } else {
      model.addAttribute("error", "Неверный логин или пароль");
      return "login";
    }
  }

  @GetMapping("/register")
  public String showRegisterForm() {
    return "register";
  }

  @PostMapping("/register")
  public String register(@RequestParam String username,
                         @RequestParam String password,
                         Model model) {
    userService.register(username, password);
    model.addAttribute("message", "Пользователь зарегистрирован! Войдите в систему");
    return "login";
  }

  @GetMapping("/chatroom")
  public String chatroom(HttpSession session, Model model) {
    // Проверяем, что пользователь залогинен
    String username = (String) session.getAttribute("username");
    if (username == null) {
      return "redirect:/"; // если не залогинен → login
    }

    model.addAttribute("username", username);
    return "chatroom"; // Spring ищет src/main/resources/templates/chatroom.html
  }

}

