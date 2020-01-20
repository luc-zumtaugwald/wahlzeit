package org.wahlzeit.extension;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * AnimalManagerTest
 */
public class AnimalManagerTest {

    private AnimalManager manager;

    @Before
	public void initAnimalManager() {
		manager = AnimalManager.getInstance();
	}
	
	@Test
	public void testRootType() throws AnimalTypeNotFoundException {
		Animal animal = manager.createAnimal(AnimalManager.ROOT_TYPE_NAME);
		assertTrue(animal.getType().getName().equals(AnimalManager.ROOT_TYPE_NAME));
    }
    @Test
	public void testIsSubTypeFalse() throws AnimalTypeNotFoundException {
		Animal animal = manager.createAnimal(AnimalManager.ROOT_TYPE_NAME);
		assertFalse(animal.getType().isSubtype());
    }
    @Test
	public void testIsSubTypeTrue() throws AnimalTypeNotFoundException {
        manager.addAnimalType("Cat", 15);
		Animal animal = manager.createAnimal("Cat");
		assertTrue(animal.getType().isSubtype());
    }

    @Test
	public void testCorrectSupertype() throws AnimalTypeNotFoundException {
        manager.addAnimalType("Reptile", 30);
        manager.addAnimalType("Legoan", "Reptile", 34);
		Animal legoan = manager.createAnimal("Legoan");
        assertTrue(legoan.getType().superType.getName().equals("Reptile"));
        Animal reptile = manager.createAnimal("Reptile");
        assertTrue(reptile.getType().superType.getName().equals(AnimalManager.ROOT_TYPE_NAME));
    }

    @Test (expected = AnimalTypeNotFoundException.class)
	public void testAnimalNotFounc() throws AnimalTypeNotFoundException {
        manager.createAnimal("Sauerkraut");
	}
}