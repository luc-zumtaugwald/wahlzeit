package org.wahlzeit.extension;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.extension.AnimalPhoto;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class AnimalPhotoFactoryTest {
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
	public void testCreatePhotoType() throws PhotoCreationFailedException{
		Photo photo = factory.createPhoto();
		assertTrue(photo instanceof AnimalPhoto);
	}

	@Test
	public void testCreatePhotoCityname() throws PhotoCreationFailedException{
		AnimalPhoto photo = (AnimalPhoto) factory.createPhoto();
		assertTrue(photo.getAnimal().getType().getName().equals("Unknown"));
	}

}
