package xyz.fivemillion.todomanager.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity(name = "tbl_todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String todo;
    private String message;
    private String cron;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Todo(Long id, String todo, String message, String cron, User user) {
        this.id = id;
        this.todo = todo;
        this.message = message;
        this.cron = cron;
        this.user = user;
    }

    public void updateCron(String newCronExp) {
        this.cron = newCronExp;
    }
}
