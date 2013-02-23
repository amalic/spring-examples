package org.amalic.orm.dao.impl;

import java.util.List;

import org.amalic.orm.dao.ContactDAO;
import org.amalic.orm.model.Contact;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImpl implements ContactDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addContact(Contact contact) {
		if(null == contact)
			throw new IllegalArgumentException("Contact must not be null");
		
		sessionFactory.getCurrentSession().save(contact);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Contact> listContact() {
		return (List<Contact>)sessionFactory.getCurrentSession().createQuery("from Contact").list();
	}
	
	@Override
	public Contact loadContact(Integer id) {
		if(null == id)
			throw new IllegalArgumentException("id must not be null");
		
		return (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
	}

	@Override
	public void removeContact(Contact contact) {
		if(null == contact)
			throw new IllegalArgumentException("Contact must not be null");
		
		sessionFactory.getCurrentSession().delete(contact);
	}

}
