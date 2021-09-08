package xyz.fivemillion.todomanager.dto.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class SlackPayload extends Payload {

    private String text;
    private String username;
    private String imgUrl;

    @Builder
    public SlackPayload(String text, String username, String imgUrl) {
        this.text = text;
        this.username = username;
        this.imgUrl = imgUrl;
    }
}
