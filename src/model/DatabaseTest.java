package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Customer;
import model.Database;

class DatabaseTest {

	@Test
	void testAddCustomer() {
		Database db = new Database();
		db.addCustomer("Bob", "Software Engineer");
		db.addCustomer("Jack", "Dancer");
		Customer c1 = db.getCustomer(1);
		Customer c2 = db.getCustomer(2);
		
		assertEquals("Bob", c1.getName());
		assertEquals("Software Engineer", c1.getOccupation());
		assertEquals("Jack", c2.getName());
		assertEquals("Dancer", c2.getOccupation());

	}
	
	@Test
	void testUpdateCustomer() {
		Database db = new Database();
		Customer bob = db.addCustomer("Bob", "Software Engineer");
		db.addCustomer("Jack", "Dancer");
		bob.setName("Bob2");
		db.updateCustomer(bob);
		Customer newBob = db.getCustomer(1);
		
		assertEquals("Bob2", newBob.getName());
	}


}
