package io.ascending.training.repository;

import io.ascending.training.domain.Car;
import io.ascending.training.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
//T=Car, ID=Long
public class CarDaoImpl implements CarDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Car save(Car c) {
        Session session = sessionFactory.getCurrentSession();
        session.save(c);
        return c;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        String hql = "FROM User";
        Session s = sessionFactory.getCurrentSession();
        TypedQuery<Car> query=s.createQuery(hql);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Car findByIdEager(Long id) {
        String hql = "FROM Car c LEFT JOIN FETCH c.images where c.id = :carId";
        Session s =sessionFactory.getCurrentSession();
        TypedQuery<Car> query=s.createQuery(hql);
        query.setParameter("carId",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Car findById(Long id) {
        String hql = "FROM Car c where c.id = :carId";
        Session s =sessionFactory.getCurrentSession();
        TypedQuery<Car> query=s.createQuery(hql);
        query.setParameter("carId",id);
        return query.getSingleResult();
    }
}
