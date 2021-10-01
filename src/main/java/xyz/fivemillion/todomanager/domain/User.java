package xyz.fivemillion.todomanager.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String userId;

    private String webhookUrl;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    @OneToMany(mappedBy = "user")
    private List<Todo> todos = new ArrayList<>();

    @Builder
    public User(String userId, String webhookUrl, Platform platform) {
        this.userId = userId;
        this.webhookUrl = webhookUrl;
        this.platform = platform;
    }
}
