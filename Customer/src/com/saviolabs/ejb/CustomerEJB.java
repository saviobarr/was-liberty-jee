package com.saviolabs.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.saviolabs.bo.Customer;

@Stateless
public class CustomerEJB {
	@PersistenceContext(unitName="Customer")
	EntityManager em;

	public void insert(Customer customer){
		em.persist(customer);
	}

	public List<Customer> listAll(){
		Query query = em.createQuery("SELECT c FROM Customer c");
		return (List<Customer>) query.getResultList();
	}

}
