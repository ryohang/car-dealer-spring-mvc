//package io.ascending.training.repository;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class CRUDDaoImpl<T,ID>  implements CRUDDao {
//    @Autowired
//    private SessionFactory sessionFactory;
//    @Override
//    public Object save(Object o) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(o);
//        return o;
//    }
//
//    @Override
//    public List findAll() {
//        return null;
//    }
//
//    @Override
//    public Object findById(Object o) {
//        return null;
//    }
//}
