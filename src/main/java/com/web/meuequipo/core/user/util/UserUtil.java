package com.web.meuequipo.core.user.util;

import com.web.meuequipo.core.user.User;
import com.web.meuequipo.core.user.dto.response.UserResponse;

public class UserUtil {

    public static UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setSurnames(user.getSurnames());
        userResponse.setEmail(user.getEmail());
        userResponse.setActive(user.isActive());

        return userResponse;
    }
}
