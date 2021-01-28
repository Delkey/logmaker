package logmaker.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class LogHackOutTest {
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void createTable() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE log_hackout ");
        queryBuilder.append("(");
        queryBuilder.append("id INT PRIMARY KEY AUTO_INCREMENT, memNo INT UNIQUE KEY NOT NULL, birthYear INT(4), ");
        queryBuilder.append("sexFl ENUM('m', 'w', ''), reasonCd VARCHAR(10), ");
        queryBuilder.append("reason VARCHAR(255), reasonDesc VARCHAR(255), hackDt DATETIME NOT NULL");
        queryBuilder.append(")");
        String createQuery = queryBuilder.toString();
        em.createNativeQuery(createQuery).executeUpdate();
    }

    @Test
    @Transactional
    void dropTable() {
        String createQuery = "DROP TABLE log_hackout";
        em.createNativeQuery(createQuery).executeUpdate();
    }
}
