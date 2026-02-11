package mitiok.ddns.net.chat.service;

import mitiok.ddns.net.chat.model.User;
import mitiok.ddns.net.chat.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User register(String username, String password) {
    // В будущем здесь можно хешировать пароль
    User user = new User(username, password);
    return userRepository.save(user);
  }

  public boolean checkLogin(String username, String password) {
    Optional<User> userOpt = userRepository.findByUsername(username);
    return userOpt.map(u -> u.getPasswordHash().equals(password)).orElse(false);
  }
}
