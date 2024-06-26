package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("select distinct u from User u left join fetch u.car");
      return query.getResultList();
   }

   @Override
   public List<User> getUserWhoHasCarModelAndSeries(String model, int series) {
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("select distinct u from User u left join fetch u.car where u.car.model = :m and u.car.series = :s");
      query.setParameter("m", model).setParameter("s", series);
      return query.getResultList();
   }
}
