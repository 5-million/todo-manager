package xyz.fivemillion.todomanager.dto.todo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoInfo {

    private String todo;
    private String message;
}
