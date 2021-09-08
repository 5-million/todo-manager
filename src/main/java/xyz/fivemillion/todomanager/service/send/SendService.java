package xyz.fivemillion.todomanager.service.send;

import xyz.fivemillion.todomanager.domain.Todo;

public interface SendService {

    public void send(Todo todo);
    public void sendMorning(Todo[] todos);
    public void sendNight(Todo[] todos);
}
