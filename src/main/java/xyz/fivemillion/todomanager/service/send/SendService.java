package xyz.fivemillion.todomanager.service.send;

import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.domain.User;

public interface SendService {

    void send(Todo todo);
    void sendAtMorning(User user, Todo[] todos);
    void sendAyNight(User user, Todo[] todos);
}
