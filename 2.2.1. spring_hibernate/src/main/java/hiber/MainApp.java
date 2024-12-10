package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Adam", "Potter", "potter@hogwarts.com");
      User user2 = new User("Bambo", "Granger", "granger@hogwarts.com");
      User user3 = new User("Cermius", "Weasley", "weasley@hogwarts.com");
      User user4 = new User("Deminion", "Lupin", "lupin@hogwarts.com");

      Car car1 = new Car("Mazda", 2021);
      Car car2 = new Car("Porshe", 1001);
      Car car3 = new Car("Opel", 7);
      Car car4 = new Car("Hyidai", 290);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      // Users with cars
      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1 ");
      }

      // Find user
      System.out.println(userService.getUserByCar("Nimbus", 1001));
      System.out.println("2 ");

      // Not found
      try {
         User notFoundUser = userService.getUserByCar("Broom", 90);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println("3 ");
      }

      context.close();
   }
}