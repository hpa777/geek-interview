package lesson5.entity;

import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManagerFactory;

public class Main {

    public static final String HIBERNATE_CFG = "hibernate.cfg.xml";

    private static EntityManagerFactory getEntityManager() {
        return new Configuration()
                .configure(HIBERNATE_CFG)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        Dao<Student> studentDao = new Dao<>(getEntityManager(), Student.class);
        studentDao.insert(new Student(null, "Test", "Test"));
        studentDao.insert(new Student(null, "Test3", "Test4"));
        studentDao.update(new Student(1L, "Test2", "Test2"));
        studentDao.deleteById(2L);
        studentDao.findAll().forEach(student -> System.out.println(student.toString()));
    }
}
