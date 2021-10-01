package xyz.fivemillion.todomanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.fivemillion.todomanager.domain.User;
import xyz.fivemillion.todomanager.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getById(String id) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()) return null;
        return opt.get();
    }
}
