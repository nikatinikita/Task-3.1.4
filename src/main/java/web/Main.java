package web;

import web.config.RestTemplateConfig;
import web.consumer.Consumer;
import web.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RestTemplateConfig.class);
        Consumer consumer = context.getBean("consumer", Consumer.class);
        consumer.getAllUsers();
        consumer.createUser(new User(3L   , "James", "Brown", (byte) 40));
        consumer.editUser(new User(3L, "Thomas", "Shelby", (byte) 30));
        consumer.deleteUser(3L);
        System.out.println(Consumer.result);
    }
}
