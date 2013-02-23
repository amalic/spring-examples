package org.amalic.service.impl;

import java.util.List;

import org.amalic.orm.dao.ContactDAO;
import org.amalic.orm.model.Contact;
import org.amalic.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * read more about Transactions here -> http://www.ibm.com/developerworks/java/library/j-ts1/index.html
 * 
 * @author amalic
 */

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactDAO contactDAO;

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void addContact(Contact contact) {
		contactDAO.addContact(contact);
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public Contact loadContact(Integer id) {
		return contactDAO.loadContact(id);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<Contact> listContact() {
		return contactDAO.listContact();
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void removeContact(Integer id) {
		Contact contact = contactDAO.loadContact(id);
		contactDAO.removeContact(contact);
	}

}
