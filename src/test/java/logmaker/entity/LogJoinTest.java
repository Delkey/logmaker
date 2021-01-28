package logmaker.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class LogJoinTest {
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void createTable() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE log_join ");
        queryBuilder.append("(");
        queryBuilder.append("id INT PRIMARY KEY AUTO_INCREMENT, memNo INT UNIQUE KEY, birthYear INT(4), sexFl ENUM('m', 'w', ''), ");
        queryBuilder.append("entryDt DATETIME");
        queryBuilder.append(")");
        String createQuery = queryBuilder.toString();
        em.createNativeQuery(createQuery).executeUpdate();
    }

    @Test
    @Transactional
    void dropTable() {
        String createQuery = "DROP TABLE log_join";
        em.createNativeQuery(createQuery).executeUpdate();
    }
}
