package io.san.service;

import io.san.entity.User;
import io.san.exception.BadRequestException;
import io.san.exception.ResourceNotFoundException;
import io.san.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User findOne(String id) {
        User existing = repository.findOne(id);
        if (existing == null) {
            throw new ResourceNotFoundException("User with id " + id + " doesn't exist.");
        }
        return existing;
    }

    @Transactional
    public User create(User user) {
        User existing = repository.findByEmail(user.getEmail());
        if (existing != null) {
            throw new BadRequestException("User with email " + user.getEmail() + " already exists.");
        }
        return repository.create(user);
    }


    @Transactional
    public User update(String id, User user) {
        User existing = repository.findOne(id);
        if (existing == null) {
            throw new ResourceNotFoundException("User with id " + id + " doesn't exist.");
        }
        return repository.update(user);
    }

    @Transactional
    public void delete(String id) {
        User existing = repository.findOne(id);
        if (existing == null) {
            throw new ResourceNotFoundException("User with id " + id + " doesn't exist.");
        }
        repository.delete(existing);
    }
}