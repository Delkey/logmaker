package logmaker.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class LogOrderGoodsTest {
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void createTable() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE log_orderGoods ");
        queryBuilder.append("(");
        queryBuilder.append("id INT PRIMARY KEY AUTO_INCREMENT, memNo INT, memId VARCHAR(30), birthYear INT(4), sexFl ENUM('m', 'w', ''), ");
        queryBuilder.append("cateCd CHAR(12), cateNm1 VARCHAR(100), cateNm2 VARCHAR(100), cateNm3 VARCHAR(100), ");
        queryBuilder.append("orderNo VARCHAR(16), goodsNo INT(10), goodsNm VARCHAR(100), ");
        queryBuilder.append("paymentDt DATETIME NOT NULL, ");
        queryBuilder.append("goodsCnt INT(10) DEFAULT 0, ");
        queryBuilder.append("goodsPrice DECIMAL(12, 2) DEFAULT 0.00, ");
        queryBuilder.append("regDt DATETIME NOT NULL, modDt DATETIME");
        queryBuilder.append(")");
        String createQuery = queryBuilder.toString();
        em.createNativeQuery(createQuery).executeUpdate();
    }

    @Test
    @Transactional
    void dropTable() {
        String createQuery = "DROP TABLE log_orderGoods";
        em.createNativeQuery(createQuery).executeUpdate();
    }
}
