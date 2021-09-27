package xyz.fivemillion.todomanager.domain;

import org.springframework.stereotype.Component;
import xyz.fivemillion.todomanager.util.PropertyUtil;

@Component
public class TodoList {

    private Todo[] todos;
    private int NUMBER_OF_TODOS = 4;

    public TodoList(PropertyUtil propertyUtil) {
        StringBuilder sb = new StringBuilder();

        todos = new Todo[NUMBER_OF_TODOS + 1];
        for (int i = 0; i < NUMBER_OF_TODOS; i++) {
            String key = "todos." + i;
            Long id = Long.parseLong(propertyUtil.getProperty(key + ".id"));
            String todo = propertyUtil.getProperty(key + ".todo");
            String message = propertyUtil.getProperty(key + ".message");
            String cron = propertyUtil.getProperty(key + ".cron");
            todos[i] = Todo.create(id, todo, message, cron);

            if (i != 3)
                sb.append(todo + "\n");
        }

        todos[NUMBER_OF_TODOS] =
                Todo.create(Long.valueOf(NUMBER_OF_TODOS), "오늘의 할 일", sb.toString(), "0 50 08 * * ?");
    }

    public Todo getTodo(int index) {
        return todos[index];
    }

    public Todo[] getTodoList() {
        return todos;
    }
}
