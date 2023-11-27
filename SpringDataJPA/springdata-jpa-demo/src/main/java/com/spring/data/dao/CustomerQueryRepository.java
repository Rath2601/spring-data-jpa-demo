package com.spring.data.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.data.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class CustomerQueryRepository {

	@PersistenceContext
	EntityManager em;

//	Query sub-types:
//		* TYPED QUERY
//		* NAMED QUERY

//	QUERY - JPQL 

	public List<Customer> getCustomerByCity(String city) {
		Query jpqlQuery = em.createQuery("SELECT c FROM CUSTOMER_TABLE c WHERE c.city=:city");
		// entity name should match with the table name in JPQL query
		jpqlQuery.setParameter("city", city);
		return (List<Customer>) jpqlQuery.getResultList(); // we need to cast additionally
	}

//	TYPEDQUERY - JPQL (i)

	// here we don't need to cast additionally , If we use TypedQuery<OurEntity>
	// avoiding possible casting exceptions

	public List<Customer> getCustomerByFname(String fname) {
		TypedQuery<Customer> typedQuery = em.createQuery("SELECT c FROM CUSTOMER_TABLE c WHERE c.fname=:fname",
				Customer.class);
		typedQuery.setParameter("fname", fname);
		List<Customer> customers = typedQuery.getResultList();
		return customers;
	}

//   NAMEDQUERY - JPQL (ii)

	// Queries in entity or application.properties or orm.xml
	// nameduQueries must have unique names

	public List<Customer> getCustomerByLname(String lname) {
		Query namedQuery = em.createNamedQuery("Customer.findByLname");
		namedQuery.setParameter("lname", lname);
		List<Customer> customers = namedQuery.getResultList();
		return customers;

	}

// NATIVE QUERY 

	public List<Map<String, Object>> getCustomerByCountry(String country) {
		Query nativeQuery = em.createNativeQuery("SELECT * FROM CUSTOMER_TABLE WHERE COUNTRY =:country");
		nativeQuery.setParameter("country", country);
		List<Object[]> customersList = nativeQuery.getResultList();
		 List<Map<String, Object>> customers = new ArrayList<>();

		    for (Object[] result : customersList) {
		        Map<String, Object> customerMap = new LinkedHashMap<>();
		        customerMap.put("id", result[0]);
		        customerMap.put("city", result[1]);
		        customerMap.put("country", result[2]);
		        customerMap.put("dob", result[3]);
		        customerMap.put("email", result[4]);
		        customerMap.put("fname", result[5]);
		        customerMap.put("lname", result[6]);
		        customerMap.put("pnum", result[7]);
		        customerMap.put("customerId", result[8]);
		        customers.add(customerMap);
		    }


		return customers;
	}
}