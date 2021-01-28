package logmaker.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class LogOrderSalesTest {
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void createTable() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE log_orderSales ");
        queryBuilder.append("(");
        queryBuilder.append("id INT PRIMARY KEY AUTO_INCREMENT, memNo INT, memId VARCHAR(30), birthYear INT(4), sexFl ENUM('m', 'w', ''), ");
        queryBuilder.append("cateCd CHAR(12), cateNm1 VARCHAR(100), cateNm2 VARCHAR(100), cateNm3 VARCHAR(100), ");
        queryBuilder.append("goodsNo INT(10), goodsNm VARCHAR(100), ");
        queryBuilder.append("orderYMD INT(8), mallSno SMALLINT(5), kind ENUM('order', 'refund') DEFAULT 'order', type ENUM('goods', 'delivery') DEFAULT 'goods', scmNo INT(10), purchaseNo INT(10), orderIP VARCHAR(15), orderNo VARCHAR(16), ");
        queryBuilder.append("relationSno INT(10), orderHour TINYINT(2), orderDevice ENUM('pc', 'mobile', 'write') DEFAULT 'pc', orderMemberFl ENUM('y', 'n') DEFAULT 'y', ");
        queryBuilder.append("orderTaxFl ENUM('y', 'n') DEFAULT 'y', orderGender  ENUM('male', 'female', 'etc') DEFAULT 'etc', orderAge TINYINT(2) DEFAULT 0, orderArea VARCHAR(10), orderSettleKind VARCHAR(5), ");
        queryBuilder.append("goodsCnt INT(10) DEFAULT 0, ");
        queryBuilder.append("goodsPrice DECIMAL(12, 2) DEFAULT 0.00, costPrice DECIMAL(12, 2) DEFAULT 0.00, goodsDcPrice DECIMAL(12, 2) DEFAULT 0.00, ");
        queryBuilder.append("divisionUseDeposit DECIMAL(12, 2) DEFAULT 0.00, divisionUseMileage DECIMAL(12, 2) DEFAULT 0.00, ");
        queryBuilder.append("deliveryPrice DECIMAL(12, 2) DEFAULT 0.00, deliveryDcPrice DECIMAL(12, 2) DEFAULT 0.00, divisionDeliveryUseDeposit DECIMAL(12, 2) DEFAULT 0.00, divisionDeliveryUseMileage DECIMAL(12, 2) DEFAULT 0.00,");
        queryBuilder.append("refundGoodsPrice DECIMAL(12, 2) DEFAULT 0.00, refundDeliveryPrice DECIMAL(12, 2) DEFAULT 0.00, ");
        queryBuilder.append("refundUseDeposit DECIMAL(12, 2) DEFAULT 0.00, refundUseMileage DECIMAL(12, 2) DEFAULT 0.00, refundFeePrice DECIMAL(12, 2) DEFAULT 0.00, ");
        queryBuilder.append("regDt DATETIME NOT NULL, modDt DATETIME");
        queryBuilder.append(")");
        String createQuery = queryBuilder.toString();
        em.createNativeQuery(createQuery).executeUpdate();
    }

    @Test
    @Transactional
    void dropTable() {
        String createQuery = "DROP TABLE log_orderSales";
        em.createNativeQuery(createQuery).executeUpdate();
    }
}
