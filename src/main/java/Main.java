import entities.Customer;
import entities.Image;
import orm.Connector;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connector.createConnect("root", "root", "classic_model_test");

        Connection connection = Connector.getConnection();

        EntityManager<Customer> customerManager = new EntityManager<>(connection);
        EntityManager<Image> imageManager = new EntityManager<>(connection);

//        Customer customer = new Customer("oyoy", "oy", "oy", "0888888", "oyoyoy 123", null, "Nice", null, "8800", "FR", 1370, 88.0);
//
//        customerManager.persist(customer);
//
//        Image image = new Image("yoyo.jpg", "Yo Yo", "yoyoView");
//
//        imageManager.persist(image);
//        customerManager.persist(customer);
//        customer.setCustomerNumber(1);
//        customerManager.persist(customer);
//
//        Customer found = customerManager.findFirst(Customer.class);
//
//        System.out.println(found);

//        customerManager.find(Customer.class, "credit_limit > 100000").forEach(c -> System.out.println(c.getCustomerFirstName()));
    }
}
