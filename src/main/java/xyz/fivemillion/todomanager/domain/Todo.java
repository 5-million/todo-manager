package xyz.fivemillion.todomanager.domain;

import lombok.Getter;

@Getter
public class Todo {

    private String todo;
    private String message;

    public static Todo create(String todo, String message) {
        Todo newTodo = new Todo();
        newTodo.todo = todo;
        newTodo.message = message;

        return newTodo;
    }
}
