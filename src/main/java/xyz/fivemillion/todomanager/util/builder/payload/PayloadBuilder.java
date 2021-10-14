package xyz.fivemillion.todomanager.util.builder.payload;


import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.dto.payload.Payload;

public interface PayloadBuilder <T extends Payload> {

   T build(Todo todo);
   T buildMorningMsg(Todo[] todos);
   T buildNightMsg(Todo[] todos);
}
