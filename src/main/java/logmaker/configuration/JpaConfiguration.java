package logmaker.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JpaConfiguration {
    public final String DATA_SOURCE = "dataSource";
    public final String ENTITY_MANAGER_FACTORY = "entityManagerFactory";
    public final String TRANSACTION_MANAGER = "transactionManager";

    @Bean(name = DATA_SOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean(name = ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean sqlSessionFactory(EntityManagerFactoryBuilder builder, @Named(DATA_SOURCE) final DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean =  builder.dataSource(dataSource).packages("logmaker.entity").build();
        factoryBean.setPersistenceUnitName("logmaker-manager-factory");
        return factoryBean;
    }

    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
