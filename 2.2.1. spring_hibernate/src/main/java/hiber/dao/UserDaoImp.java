package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getWithAuto(String model, int series) {
      String hql = "select u " +
              "from User u " +
              "left JOIN FETCH u.car c " +
              "WHERE c.model = :model " +
              "AND c.series = :series";
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hql, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultList();
   }

   @Override
   public long getCarID(String model, int series) {
      String hql3 = "select id " +
              "from Car c " +
              "where c.model = :model " +
              "and c.series = :series";
      Query query=sessionFactory.getCurrentSession().createQuery(hql3);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.executeUpdate(); //ошибка тут!
   }

}
