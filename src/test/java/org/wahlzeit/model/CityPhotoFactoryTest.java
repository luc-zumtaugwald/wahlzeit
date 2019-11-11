package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.services.OfyService;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class CityPhotoFactoryTest {
	@ClassRule
	public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider())
			.around(new RegisteredOfyEnvironmentProvider());
	private PhotoFactory factory;
	

	@Before
	public void initPhotoManager() {
		PhotoFactory.initialize();
		factory = PhotoFactory.getInstance();
	}
	
	@Test
	public void testCreatePhotoType(){
		Photo photo = factory.createPhoto();
		assertTrue(photo instanceof CityPhoto);
	}

	@Test
	public void testCreatePhotoCityname(){
		CityPhoto photo = (CityPhoto) factory.createPhoto();
		assertTrue(photo.getCityName().equals("unknown"));
	}

	@Test
	public void testCreatePhotoInhabitantCount(){
		CityPhoto photo = (CityPhoto) factory.createPhoto();
		assertTrue(photo.getInhabitantCount()==0);
	}
}
