package com.skilldistillery.stack.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class FunctionTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Function function;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAStack");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		function = em.find(Function.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		function = null;
		
	}

	@Test
	void test_Function_entity_mapping() {
		assertNotNull(function);
		assertEquals("Java meet up", function.getName());
		assertEquals(false, function.isCancelled());
		assertEquals(true, function.isEnabled());
		assertEquals("discussing the philosphy of java", function.getDescription());
		assertEquals(10, function.getCap());
		
	}

}