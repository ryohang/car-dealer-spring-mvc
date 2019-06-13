package io.ascending.training.repository;

import io.ascending.training.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository

public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public User save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        String hql = "FROM User";
        Session s = sessionFactory.getCurrentSession();
        TypedQuery<User> query=s.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public User findByIdEager(Long id) {
        return null;
    }

    @Override
    public User findById(Long id) {
        String hql = "FROM User u where u.id = :userId";
        Session s =sessionFactory.getCurrentSession();
        TypedQuery<User> query=s.createQuery(hql);
        query.setParameter("userId",id);
        return query.getSingleResult();
    }


    public User findByEmailIgnoreCase(String email){
        return null;
    }

    public User findByUsernameIgnoreCase(String email){
        return null;
    }
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public User save(User user) {
//        sessionFactory.getCurrentSession().save(user);
//        return user;
//    }
//
//    public List<User> findAll() {
//        String hql = "FROM User";
//        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hql);
//        return query.getResultList();
//    }
//
//    @Override
//    public User findByIdEager(Long id) {
//        String hql = "FROM User u JOIN FETCH u.images where u.id = ?1";
//        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hql).setParameter(1,id);
//        return query.getSingleResult();
//    }
//
//    @Override
//    public User findById(Long id) {
//        String hql = "FROM User u where u.id = ?1";
//        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hql).setParameter(1,id);
//        return query.getSingleResult();
//    }
}
