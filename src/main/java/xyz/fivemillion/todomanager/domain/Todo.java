package xyz.fivemillion.todomanager.domain;

import lombok.Getter;

@Getter
public class Todo {

    private Long id;
    private String todo;
    private String message;
    private String cron;

    public static Todo create(Long id, String todo, String message, String cron) {
        Todo newTodo = new Todo();
        newTodo.id = id;
        newTodo.todo = todo;
        newTodo.message = message;
        newTodo.cron = cron;

        return newTodo;
    }
}
