package io.ascending.training.repository;

import io.ascending.training.domain.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public Image save(Image img) {
        Session session = sessionFactory.getCurrentSession();
        session.save(img);
        return img;
    }

    @Override
    public List<Image> findAll() {
        return null;
    }

    @Override
    public Image findById(Long aLong) {
        return null;
    }
}
