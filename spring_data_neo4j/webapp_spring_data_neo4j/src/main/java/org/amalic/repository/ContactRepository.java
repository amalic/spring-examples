package org.amalic.repository;

import java.util.ArrayList;
import java.util.List;

import org.amalic.domain.Contact;
import org.neo4j.helpers.collection.IteratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
	
	@Autowired
    private Neo4jOperations template;
	
	public void addContact(Contact contact) {
		template.save(contact);
	}
	
	public List<Contact> loadContacts() {
		return new ArrayList<Contact>(
				IteratorUtil.asCollection(
						template.findAll(Contact.class)));
	}
	
	public Contact loadContact(Long id) {
		return template.findOne(id, Contact.class);
	}
	
	public void removeContact(Contact contact) {
		template.delete(contact);
	}

}
