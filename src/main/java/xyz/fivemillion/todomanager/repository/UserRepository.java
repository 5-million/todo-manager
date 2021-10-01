package xyz.fivemillion.todomanager.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import xyz.fivemillion.todomanager.domain.User;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public Optional<User> findById(String id) {
        return Optional.ofNullable(em.find(User.class, id));
    }
}
