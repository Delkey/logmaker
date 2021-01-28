package logmaker.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class LogVisitTest {
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void createTable() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE log_visit ");
        queryBuilder.append("(");
        queryBuilder.append("id INT PRIMARY KEY AUTO_INCREMENT, visitNo INT(10), memNo INT, memId VARCHAR(30), birthYear INT(4), sexFl ENUM('m', 'w', ''), ");
        queryBuilder.append("visitSiteKey VARCHAR(32), visitIP VARCHAR(15), mallSno SMALLINT(5) DEFAULT 1, ");
        queryBuilder.append("visitReferer VARCHAR(200), visitInflow VARCHAR(10), visitDevice ENUM('pc', 'mobile') DEFAULT 'pc', ");
        queryBuilder.append("visitOS VARCHAR(15), visitBrowser VARCHAR(10), ");
        queryBuilder.append("visitYear SMALLINT(4), visitMonth TINYINT(2), visitDay TINYINT(2), visitHour TINYINT(2), ");
        queryBuilder.append("visitPageView SMALLINT(5), regDt DATETIME NOT NULL, modDt DATETIME");
        queryBuilder.append(")");
        String createQuery = queryBuilder.toString();
        em.createNativeQuery(createQuery).executeUpdate();
    }

    @Test
    @Transactional
    void dropTable() {
        String createQuery = "DROP TABLE log_visit";
        em.createNativeQuery(createQuery).executeUpdate();
    }
}
