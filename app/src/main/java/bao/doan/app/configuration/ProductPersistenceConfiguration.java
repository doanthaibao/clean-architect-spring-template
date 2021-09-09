package bao.doan.app.configuration;

import java.util.Objects;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
    basePackages = "bao.doan.productpersistence.repository",
    entityManagerFactoryRef = "productEntityManagerFactory",
    transactionManagerRef = "productTransactionManager"
)
@RequiredArgsConstructor
@EnableTransactionManagement(proxyTargetClass = false)
public class ProductPersistenceConfiguration {

  private final DataSource dataSource;

  //Config datasource here => use default or customize
  @Primary
  @Bean(name = "productEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(
      final EntityManagerFactoryBuilder builder) {
    return builder.dataSource(dataSource).packages("bao.doan.productpersistence").build();
  }

  @Primary
  @Bean("productTransactionManager")
  public PlatformTransactionManager productTransactionManager(
      final @Qualifier("productEntityManagerFactory")
          LocalContainerEntityManagerFactoryBean productEntityManagerFactory) {
    final EntityManagerFactory emf = Objects.requireNonNull(
        productEntityManagerFactory.getObject(), "EMF can not be null");
    return new JpaTransactionManager(emf);

  }
}
