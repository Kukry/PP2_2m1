package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      User user5 = new User("John", "Doe", "johndoe@mail.ru");
      Car car1 = new Car("Ferrari", 50);
      Car car2 = new Car("Lamborgini", 40);
      Car car3 = new Car("Moskvich", 38);
      Car car4 = new Car("BMW", 6);
      Car car5 = new Car("Moskvich", 38);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);
      user5.setCar(car5);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
      userService.add(user5);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString());
         System.out.println();
      }

      users = userService.getUserWhoHasCarModelAndSeries("Moskvich", 38);
      for (User user : users) {
         System.out.println(user.toString());
         System.out.println();
      }

      context.close();
   }
}
