package org.amalic.orm.dao;

import java.util.List;

import org.amalic.orm.model.Contact;

public interface ContactDAO {
	
	public void addContact(Contact contact);
	
	public List<Contact> listContact();
	
	public Contact loadContact(Integer id);
	
	public void removeContact(Contact contact);

}
