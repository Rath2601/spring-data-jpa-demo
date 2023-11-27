package com.spring.data.dao;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.data.entity.Customer;

@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	CustomerRepository repo;

	@Test
	public void saveMethod() { // this will also be reflected in MySql db
		Customer c = new Customer(1L, 1000L, "karthick", "subburaj", "ks@gmail.com", 7967346334L, "Madurai", "India",
				LocalDate.of(2000, 1, 8));
		Customer c1 = new Customer(null, null, "Kamal", "hassan", "kh@gmail.com", 9849389733L, "Paramakudi", "India",
				LocalDate.of(2000, 01, 13));

		Customer c2 = new Customer(null, null, "Bharani", "Bala", "barani@gmail.com", 9232943439L, "Newyork", "USA",
				LocalDate.of(2000, 01, 13));

		List<Customer> customerList = new LinkedList<Customer>();
		customerList.add(c);
		customerList.add(c1);
		customerList.add(c2);

//		List<Customer> savedList = repo.saveAll(customerList);
//		List<Customer> savedList = repo.saveAll(List.of(c, c1, c2));

//		assertNotNull(savedList);
	}

	@Test
	public void updateUsingSave() { // this will also be reflected in MySql db

		Customer c1 = repo.findById(2L).get();

		c1.setCustomerId(1002L);

		repo.save(c1);
	}

	@Test
	public void multipleUpdate() { // this will also be reflected in MySql db

		Customer c = repo.findById(6L).get();

		c.setCustomerId(1001L);

		Customer c1 = repo.findById(7L).get();

		c1.setCustomerId(1002L);

		repo.saveAll(List.of(c, c1));
	}

	@Test
	public void findByCountryTest() {
		List<Customer> customers = repo.findByCountry("USA");

		for (Customer customer : customers) {
			System.out.println(customer.getFname());
		}
	}

	@Test
	public void findByDobGreaterThanTest() {
		List<Customer> customers = repo.findByDobGreaterThan((LocalDate.of(2000, 1, 1)));

		for (Customer customer : customers) {
			System.out.println(customer.getFname());
		}
	}

	@Test
	public void findByCustomerIdInTest() {
		List<Customer> customers = repo.findByCustomerIdIn(List.of(1002L, 1004L, 1006L));

		for (Customer customer : customers) {
			System.out.println(customer.getFname());
		}
	}

	@Test
	public void findByCityTest() {
		Optional<List<Customer>> customers = repo.findByCity("Madurai");

		List<Customer> savedCustomers = customers.get(); // avoid null pointer exception

		for (Customer customer : savedCustomers) {
			System.out.println(customer.getFname());
		}

	}

	@Test
	public void findDistinctCountryTest() {
		List<String> countries = repo.findDistinctCountry();

		for (String country : countries) {
			System.out.println(country);
		}

	}

	@Test
	public void findDistinctByCountryIsNotNullTest() {
		List<Customer> countries = repo.findDistinctByCountryIsNotNull();

		for (Customer country : countries) {
			System.out.println(country.getCountry());
		}

	}

	@Test
	public void findByNameContainingTest() {
		List<Customer>  customers = repo.findByFnameContaining("Bha");

		for (Customer customer : customers) {
			System.out.println(customer.getCountry());
		}

	}

	@Test
	public void findByNameLikeTest() {
		Customer customer = repo.findByFnameLike("Bharani");

		System.out.println(customer.getFname());

	}

	@Test
	public void findByDobBetweenTest() {
		List<Customer> customers = repo.findByDobBetween(LocalDate.of(1980, 01, 01), LocalDate.of(2000, 01, 01)).get();

		for (Customer customer : customers) {
			System.out.println(customer.getFname());
		}

	}
}
