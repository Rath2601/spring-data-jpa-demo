package com.spring.data.entity;

import java.time.LocalDate;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CUSTOMER_TABLE")
@NamedQuery(name = "Customer.findByLname", query = "SELECT u FROM CUSTOMER_TABLE u WHERE u.lname=:lname")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
	@SequenceGenerator(name = "customer_generator", sequenceName = "customer_table_seq", schema = "CUSTOMER_DB", allocationSize = 1, initialValue = 1)
	private Long id;

//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
//	@SequenceGenerator(name = "customer_seq", sequenceName = "customer_id_seq", schema = "CUSTOMER_DB", allocationSize = 2, initialValue = 1000)
	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "FIRST_NAME")
	private String fname;

	@Column(name = "LAST_NAME")
	private String lname;

	@Column(name = "EMAIL_ADDRESS")
	private String email;

	@Column(name = "PHONE_NUMBER")
	private Long pnum;

	@Column(name = "CITY")
	private String city;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "DATE_OF_BIRTH")
	private LocalDate dob;

}