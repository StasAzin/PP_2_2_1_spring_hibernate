package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
   }

   @Override
   public User getUserByModelAndSeries(String model, int series) {
      return sessionFactory.getCurrentSession()
              .createQuery("FROM User AS user WHERE user.car.model = :model AND user.car.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList().get(0);
   }
}
