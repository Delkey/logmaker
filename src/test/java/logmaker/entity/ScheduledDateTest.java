package logmaker.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class ScheduledDateTest {
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void createTable() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE scheduled_date ");
        queryBuilder.append("(");
        queryBuilder.append("id INT PRIMARY KEY AUTO_INCREMENT, lastScheduledDate VARCHAR(10), lastScheduledHour INT(2), ");
        queryBuilder.append("createdAt DATETIME DEFAULT CURRENT_TIMESTAMP, updatedAt DATETIME ON UPDATE CURRENT_TIMESTAMP");
        queryBuilder.append(")");
        String createQuery = queryBuilder.toString();
        em.createNativeQuery(createQuery).executeUpdate();
    }

    @Test
    @Transactional
    void dropTable() {
        String createQuery = "DROP TABLE scheduled_date";
        em.createNativeQuery(createQuery).executeUpdate();
    }
}
