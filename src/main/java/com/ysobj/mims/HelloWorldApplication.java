package com.ysobj.mims;

import org.skife.jdbi.v2.DBI;

import com.ysobj.mims.health.TemplateHealthCheck;
import com.ysobj.mims.jdbi.PeopleDao;
import com.ysobj.mims.resources.HelloWorldResource;
import com.ysobj.mims.resources.PeopleResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<HelloWorldConfiguration>() {
			public DataSourceFactory getDataSourceFactory(
					HelloWorldConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		bootstrap.addBundle(new AssetsBundle());
	}

	@Override
	public void run(HelloWorldConfiguration configuration,
			Environment environment) throws Exception {
		final HelloWorldResource resource = new HelloWorldResource(
				configuration.getTemplate(), configuration.getDefaultName());
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
				configuration.getTemplate());

		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment,
				configuration.getDataSourceFactory(), "jdbi");
		PeopleDao peopleDao = jdbi.onDemand(PeopleDao.class);
		environment.jersey().register(new PeopleResource(peopleDao));
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
	}

}
