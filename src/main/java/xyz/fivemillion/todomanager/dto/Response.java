package xyz.fivemillion.todomanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response <T> {
    T data;
}
