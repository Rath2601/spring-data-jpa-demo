package com.spring.data.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.data.dao.CustomerQueryRepository;
import com.spring.data.dao.CustomerRepository;
import com.spring.data.entity.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerRepository repo;

	@Autowired
	CustomerQueryRepository QueryRepo;

	@GetMapping
	public ResponseEntity<Customer> getCustomer(@RequestParam("id") Long id) {
		Customer customer = repo.findById(id).get();
		return ResponseEntity.ok(customer);
	}

	@GetMapping("/country/{country}")
	public ResponseEntity<List<Customer>> getCustomerByCountry(@PathVariable("country") String country) {
		List<Customer> customers = repo.findByCountry(country);
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/dob/{dob}")
	public ResponseEntity<List<Customer>> getCustomerByDobGreaterThan(@PathVariable("dob") LocalDate dob) {
		List<Customer> customers = repo.findByDobGreaterThan(dob);
		return ResponseEntity.ok(customers);
	}


	@GetMapping("/Ids/{Ids}")
	public ResponseEntity<List<Customer>> getCustomerByIds(@PathVariable("Ids") List<Long> customerIds) {
		List<Customer> customers = repo.findByCustomerIdIn(customerIds);
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<List<Customer>> getCustomerByCity(@PathVariable("city") String city) {
		List<Customer> customers = repo.findByCity(city).get();
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/query/fname/{fname}")
	public ResponseEntity<List<Customer>> getCustomerByFnameQuery(@PathVariable("fname") String fname) {
		List<Customer> customers = QueryRepo.getCustomerByFname(fname);
		return ResponseEntity.ok(customers);
	}


	@GetMapping("/query/city/{city}")
	public ResponseEntity<List<Customer>> getCustomerByCityQuery(@PathVariable("city") String city) {
		List<Customer> customers = QueryRepo.getCustomerByCity(city);
		return ResponseEntity.ok(customers);
	}


	@GetMapping("/query/lname/{lname}")
	public ResponseEntity<List<Customer>> getCustomerByLnameQuery(@PathVariable("lname") String lname) {
		List<Customer> customers = QueryRepo.getCustomerByLname(lname);
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/native/country/{country}")
	public ResponseEntity<List<Map<String, Object>>> getCustomerByNativeQuery(@PathVariable("country") String country) {
		List<Map<String, Object>> customers = QueryRepo.getCustomerByCountry(country);
		return ResponseEntity.ok(customers);
	}


	@GetMapping("/countriesList")
	public ResponseEntity<List<String>> ListOfCustomerCountries() {
		List<String> countries = repo.findDistinctCountry();
		return ResponseEntity.ok(countries);
	}

	@GetMapping("/fname/{fname}")
	public ResponseEntity<List<Customer>> getCustomerByFname(@PathVariable("fname") String fname) {
		List<Customer> customers = repo.findByFnameContaining(fname);
		return ResponseEntity.ok(customers);
	}


	@GetMapping("/dobbetween/{startDate}/{endDate}")
	public ResponseEntity<List<Customer>> getCustomerByFname(@PathVariable("startDate") LocalDate startDate,@PathVariable("endDate") LocalDate endDate ) {
		List<Customer> customers = repo.findByDobBetween(startDate, endDate).get();
		return ResponseEntity.ok(customers);
	}

	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer saveCustomer = repo.save(customer);
		return ResponseEntity.ok(saveCustomer);
	}

	@PostMapping("/customers")
	public ResponseEntity<List<Customer>> addCustomers(@RequestBody List<Customer> customers) {

		List<Customer> savedCustomers = repo.saveAll(customers);
		return new ResponseEntity<List<Customer>>(savedCustomers, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		Customer updateCustomer = repo.save(customer);
		return ResponseEntity.ok(updateCustomer);
	}

	@DeleteMapping
	public String deleteCustomer(@RequestParam("id") Long id) {
		repo.deleteById(id);
		return "Deleted successfully";
	}

}