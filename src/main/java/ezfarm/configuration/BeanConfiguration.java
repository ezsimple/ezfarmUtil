package ezfarm.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import io.mkeasy.webapp.processor.MyBatisProcessor;
import io.mkeasy.webapp.processor.ProcessorServiceFactory;

@Configuration
public class BeanConfiguration {
	
	@Autowired
	private Environment env;
	
	@Autowired
	DataSource dataSource;
    
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		final String DbType = env.getProperty("Globals.DbType");
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/"+DbType+"/**/*Mapper.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        // transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }
	
	@Bean
	public MyBatisProcessor myBatisProcessor() {
		return new MyBatisProcessor();
	}

	@Bean
	public ProcessorServiceFactory processorServiceFactory() {
		return new ProcessorServiceFactory();
	}

}
