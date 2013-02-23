package org.amalic.service.impl;

import java.util.List;

import org.amalic.orm.dao.ContactDAO;
import org.amalic.orm.model.Contact;
import org.amalic.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactDAO contactDAO;

	@Override
	@Transactional
	public void addContact(Contact contact) {
		contactDAO.addContact(contact);
	}
	
	@Override
	@Transactional
	public Contact loadContact(Integer id) {
		return contactDAO.loadContact(id);
	}

	@Override
	@Transactional
	public List<Contact> listContact() {
		return contactDAO.listContact();
	}
	
	@Override
	@Transactional
	public void removeContact(Integer id) {
		Contact contact = contactDAO.loadContact(id);
		contactDAO.removeContact(contact);
	}

}
