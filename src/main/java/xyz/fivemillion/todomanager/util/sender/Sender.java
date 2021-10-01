package xyz.fivemillion.todomanager.util.sender;

import xyz.fivemillion.todomanager.dto.payload.Payload;

public interface Sender<T extends Payload> {

    void send(String url, T payload);
}
