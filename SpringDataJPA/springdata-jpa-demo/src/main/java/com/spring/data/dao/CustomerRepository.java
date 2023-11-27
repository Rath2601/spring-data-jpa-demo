package com.spring.data.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.data.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public List<Customer> findByCountry(String country);

	public List<Customer> findByDobGreaterThan(LocalDate dob);

	public List<Customer> findByCustomerIdIn(List<Long> customerIds);

//	public Optional<Customer> findById(Long id); // FindBYid is already there
//	
//	public Customer getById(Long id); // this is also there

	public Optional<List<Customer>> findByCity (String city); 

	@Query(value = "SELECT DISTINCT c.country FROM Customer_TABLE c", nativeQuery = true)
	List<String> findDistinctCountry();

    List<Customer> findDistinctByCountryIsNotNull();

   public List<Customer>  findByFnameContaining(String name);

    Customer findByFnameLike(String name);

    Optional<List<Customer>> findByDobBetween(LocalDate startDate , LocalDate endDate);



}