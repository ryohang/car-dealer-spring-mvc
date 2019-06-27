package io.ascending.training.repository;

import io.ascending.training.domain.Authority;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.NotSupportedException;
import java.util.List;

@Service
public class AuthorityDaoImpl implements AuthorityDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Authority> findByUserId(Long id) {
        String hql = "FROM Authority a where a.user.id= :userId";
        Session s = sessionFactory.getCurrentSession();
        Query<Authority> query=s.createQuery(hql);
        query.setParameter("userId",id);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Authority save(Authority authority) {
        Session session = sessionFactory.getCurrentSession();
        session.save(authority);
        return authority;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Authority> findAll() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public Authority findById(Long aLong) {
        throw new UnsupportedOperationException("not implemented");
    }
}