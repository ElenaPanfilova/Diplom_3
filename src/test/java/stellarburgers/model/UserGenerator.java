package stellarburgers.model;

import java.util.UUID;

public class UserGenerator {

    private UserGenerator() {
    }

    public static User randomUser() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        return new User("user_" + suffix + "@yandex.ru", "pass_" + suffix, "name_" + suffix);
    }
}
