package xyz.fivemillion.todomanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.domain.User;
import xyz.fivemillion.todomanager.dto.todo.TodoRegisterRequest;
import xyz.fivemillion.todomanager.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;

    @Transactional
    public Todo register(TodoRegisterRequest request) {
        User user = userService.getById(request.getUserId());
        Todo todo = Todo.builder()
                .todo(request.getTodo())
                .message(request.getMessage())
                .cron(request.getCron())
                .user(user)
                .build();

        todoRepository.save(todo);
        return todo;
    }

    public List<Todo> getAll() {
        Optional<List<Todo>> opt = todoRepository.findAll();
        if (opt.isEmpty()) return null;
        return opt.get();
    }

    @Transactional
    public void updateCron(Long todoId, String newCronExp) {
        Optional<Todo> opt = todoRepository.findById(todoId);
        if (!opt.isEmpty()) {
            Todo todo = opt.get();
            todo.updateCron(newCronExp);
        }
    }
}
