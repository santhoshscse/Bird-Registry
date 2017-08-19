package com.saltside.bird.app;

import com.saltside.bird.api.BirdsApi;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * @author santhoshsrinivasan
 *
 */
public class BirdRegistryApplication extends Application<BirdRegistryApplicationConfig> {

	public static void main(String[] args) throws Exception {
		new BirdRegistryApplication().run(args);
	}

	@Override
	public void run(BirdRegistryApplicationConfig configuration, Environment environment) throws Exception {
		environment.jersey().register(new BirdsApi());

	}

}
