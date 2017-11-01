package io.san.repository;

import io.san.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findOne(String id);

    User findByEmail(String email);

    User create(User user);

    User update(User user);

    void delete(User user);



}
