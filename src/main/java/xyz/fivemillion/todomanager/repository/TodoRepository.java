package xyz.fivemillion.todomanager.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import xyz.fivemillion.todomanager.domain.QTodo;
import xyz.fivemillion.todomanager.domain.QUser;
import xyz.fivemillion.todomanager.domain.Todo;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    private QTodo todo = QTodo.todo1;
    private QUser user = QUser.user;

    public TodoRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Todo todo) {
        em.persist(todo);
    }

    public Optional<List<Todo>> findAll() {
        List<Todo> todos = query.select(todo)
                .from(todo)
                .join(todo.user, user).fetchJoin()
                .fetch();

        return Optional.ofNullable(todos);
    }
}
