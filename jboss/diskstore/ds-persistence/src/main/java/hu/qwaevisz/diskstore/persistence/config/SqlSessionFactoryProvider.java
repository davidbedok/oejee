package hu.qwaevisz.diskstore.persistence.config;

import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryProvider {

	@Produces
	@ApplicationScoped
	public SqlSessionFactory produceFactory() throws IOException {
		final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		final SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		return factory;
	}

}
