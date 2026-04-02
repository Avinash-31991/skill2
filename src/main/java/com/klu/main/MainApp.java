package com.klu.main;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.klu.entity.Product;
public class MainApp {
	public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        // CREATE
        Transaction tx1 = session.beginTransaction();
        Product p = new Product("Laptop", "Gaming Laptop", 70000, 5);
        session.save(p);
        tx1.commit();
        System.out.println("Inserted");

        int id = p.getId();

        // READ
        Product product = session.get(Product.class, id);
        System.out.println("Fetched: " + product.getName() + " - " + product.getPrice());

        // UPDATE
        Transaction tx2 = session.beginTransaction();
        product.setPrice(75000);
        product.setQuantity(8);
        session.update(product);
        tx2.commit();
        System.out.println("Updated");

        // DELETE
        Transaction tx3 = session.beginTransaction();
        session.delete(product);
        tx3.commit();
        System.out.println("Deleted");

        session.close();
        factory.close();
    }

}
