package com.letscard.utils.faker;

import com.letscard.jpa.model.User;

import java.time.ZoneId;

public class UserFaker extends AbstractFaker<User> {

    @Override
    public User create() {
        User user = new User();
        user.setName(getFaker().name().firstName());
        user.setSurname(getFaker().name().lastName());
        user.setDateOfBirth(getFaker().date().birthday()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        );
        user.setEmail(getFaker().internet().safeEmailAddress());
        user.setPassword(getFaker().internet().password());
        user.setSex(getFaker().random().nextBoolean());
        return user;
    }

    public User createWithPlace() {
        User user = create();
        user.addPlace(new PlaceFaker().create());
        return user;
    }
}
