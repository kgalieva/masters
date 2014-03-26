package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"dao.impl","model"})
@EnableTransactionManagement
public class PersistenceConfig {

    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws PropertyVetoException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan(new String[]{"model"});
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.hbm2ddl", "true");
                setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            }
        };
    }

}
