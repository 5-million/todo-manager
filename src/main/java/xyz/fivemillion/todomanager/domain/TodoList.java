package xyz.fivemillion.todomanager.domain;

import org.springframework.stereotype.Component;
import xyz.fivemillion.todomanager.util.PropertyUtil;

@Component
public class TodoList {

    private Todo[] todos;


    public TodoList(PropertyUtil propertyUtil) {

        todos = new Todo[3];
        for (int i = 0; i < 3; i++) {
            String key = "todos." + i;
            String todo = propertyUtil.getProperty(key + ".todo");
            String message = propertyUtil.getProperty(key + ".message");
            todos[i] = Todo.create(todo, message);
        }
    }

    public Todo getTodo(int index) {
        return todos[index];
    }

    public Todo[] getTodoList() {
        return todos;
    }
}
