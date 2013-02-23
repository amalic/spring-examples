package org.amalic.service;

import java.util.List;

import org.amalic.orm.model.Contact;

public interface ContactService {
	
	public void addContact(Contact contact);
	
	public Contact loadContact(Integer id);
	
	public List<Contact> listContact();
	
	public void removeContact(Integer id);

}
