package loker.lokerpengenkerjo.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import loker.lokerpengenkerjo.model.User;
import loker.lokerpengenkerjo.repository.RoleRepository;
import loker.lokerpengenkerjo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        List<User> user = new ArrayList<>();
        userRepository.findAll().forEach(user::add);
        return user;
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
        return user;
    }

    public User deleteAllUser(User user) {
        userRepository.deleteAll();
        return user;
    }

    public User updateUser(Long id, User user) {
        userRepository.save(user);
        return user;
    }
}
