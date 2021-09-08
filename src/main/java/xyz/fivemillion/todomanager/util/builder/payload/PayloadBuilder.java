package xyz.fivemillion.todomanager.util.builder.payload;


import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.dto.payload.Payload;

public interface PayloadBuilder <T extends Payload> {

   public T build(Todo todo);
   public T buildMorning(Todo[] todos);
   public T buildNight(Todo[] todos);
}
