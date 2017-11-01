package io.san.repository;

import io.san.entity.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findAll",
                                                                    User.class);
        return query.getResultList();
    }

    public User findOne(String id) {
        return entityManager.find(User.class, id);
    }

    public User findByEmail(String email) {

        TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmail",
                                                                    User.class);
        query.setParameter("paramEmail", email);
        List<User> resultList = query.getResultList();
        if (resultList != null && resultList.size() == 1) {
            return resultList.get(0);
        } else {
            return null;
        }
    }



    public User create(User user) {
        entityManager.persist(user);

        return user;
    }

    public User update(User user) {
        return entityManager.merge(user);
    }

    public void delete(User user) {
        entityManager.remove(user);
    }



}
