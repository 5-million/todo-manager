package xyz.fivemillion.todomanager.util.builder.payload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.dto.payload.SlackPayload;
import xyz.fivemillion.todomanager.util.builder.MessageBuilder;

@Component
@RequiredArgsConstructor
public class SlackPayloadBuilder implements PayloadBuilder<SlackPayload> {

    @Override
    public SlackPayload build(Todo todo) {
        return build(MessageBuilder.build(todo));
    }

    private SlackPayload build(String message) {
        return SlackPayload.builder().text(message).build();
    }
}
