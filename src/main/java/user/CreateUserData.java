package user;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateUserData {
    public  User random() {
        return new User(RandomStringUtils.randomAlphanumeric(8) + "@yandex.ru",
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10));
    }



    public UpdateUser randomUpdateData() {
        return new UpdateUser(RandomStringUtils.randomAlphanumeric(8) + "@yandex.ru",
                RandomStringUtils.randomAlphanumeric(10));
    }

    public User existingUser() {
        return new User("qwefdfs@yandex.ru", "122333323", "UserName");
    }
}
