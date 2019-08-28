/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworldexample;

import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Xavier Roldán <info@xavierroldan.com>
 */
public class HelloWorldExample
{

    private static SessionFactory factory;
    private static ServiceRegistry registry;

    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);
        String message = "";
        System.out.println("Introduce el mensaje: ");
        message = in.nextLine();

        try
        {
            Configuration configuration = new Configuration().configure();
            registry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(registry);

        }
        catch (Exception e)
        {
            System.err.println("Fallo al crear la sesión." + e);
        }

        Session session = factory.openSession();
        Transaction tx = null;
        Short msgId = null;
        try
        {
            tx = session.beginTransaction();
            Message ms = new Message(message);
            msgId = (Short) session.save(ms);
            List messages = session.createQuery("FROM Message").list();
            for (Object mes : messages)
            {
                System.out.println("Mensaje: " + mes);
            }
            tx.commit();
        }
        catch (HibernateException e)
        {
            if (tx != null)
            {
                tx.rollback();
                e.printStackTrace();
            }
        }
        finally
        {
            session.close();
        }
        StandardServiceRegistryBuilder.destroy((org.hibernate.service.ServiceRegistry) registry);
    }

}
