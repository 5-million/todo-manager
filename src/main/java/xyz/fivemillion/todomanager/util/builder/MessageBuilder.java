package xyz.fivemillion.todomanager.util.builder;

import xyz.fivemillion.todomanager.domain.Todo;

public class MessageBuilder {

    public static String build(Todo todo) {
        StringBuilder sb = new StringBuilder();
        sb.append("✓✓ ");
        sb.append(todo.getTodo());
        sb.append(" ✓✓\n");
        sb.append(todo.getMessage());

        return sb.toString();
    }

    public static String buildMorningMessage(Todo[] todos) {
        StringBuilder sb = new StringBuilder();

        sb.append("☀️ 오늘의 할 일\n");
        for (Todo todo : todos) {
            sb.append("✓ ");
            sb.append(todo.getTodo());
            sb.append("\n");
        }
        sb.append("🤚🏻 💻💻💻 🤚🏻");

        return sb.toString();
    }

    public static String buildNightMessage(Todo[] todos) {
        StringBuilder sb = new StringBuilder();

        sb.append("🌌 할 일은 모두 마치셨나요?\n");
        for (Todo todo : todos) {
            sb.append("✓ ");
            sb.append(todo.getTodo());
            sb.append("\n");
        }
        sb.append("🌙 💻💻💻 🌙");

        return sb.toString();
    }
}
