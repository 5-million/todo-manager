package xyz.fivemillion.todomanager.util.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.fivemillion.todomanager.dto.payload.SlackPayload;

@Slf4j
@Component
public class SlackSender implements Sender<SlackPayload>{

    private static final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void send(String url, SlackPayload payload) {
        ResponseEntity<String> response = restTemplate.postForEntity(url, payload, String.class);
        log.info(response.getStatusCode().toString());
    }
}
