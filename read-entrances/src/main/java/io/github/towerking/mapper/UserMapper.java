package io.github.towerking.mapper;

import io.github.towerking.domain.User;

public interface UserMapper {

    String selectUserNameByUserId(Integer userId);

    User selectUserByUserId(User user);
}
