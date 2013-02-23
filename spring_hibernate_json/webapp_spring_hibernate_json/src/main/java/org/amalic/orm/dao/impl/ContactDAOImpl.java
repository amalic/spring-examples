package org.amalic.orm.dao.impl;

import java.util.List;

import org.amalic.orm.dao.ContactDAO;
import org.amalic.orm.model.Contact;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImpl extends AbstractDAOImpl implements ContactDAO {
	
	@Override
	public void addContact(Contact contact) {
		if(null == contact)
			throw new IllegalArgumentException("Contact must not be null");
		
		entityManager.persist(contact);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Contact> listContact() {
		return (List<Contact>)entityManager.createQuery("from Contact c").getResultList();
	}
	
	@Override
	public Contact loadContact(Integer id) {
		if(null == id)
			throw new IllegalArgumentException("id must not be null");
		
		return entityManager.find(Contact.class, id);
	}

	@Override
	public void removeContact(Contact contact) {
		if(null == contact)
			throw new IllegalArgumentException("Contact must not be null");
		
		entityManager.remove(contact);
	}

}
