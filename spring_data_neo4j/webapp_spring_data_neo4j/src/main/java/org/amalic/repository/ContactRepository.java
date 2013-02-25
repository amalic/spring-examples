package org.amalic.repository;

import java.util.ArrayList;
import java.util.List;

import org.amalic.domain.Contact;
import org.neo4j.helpers.collection.IteratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactRepository {
	
	@Autowired
    private Neo4jOperations template;
	
	@Transactional
	public void addContact(Contact contact) {
		template.save(contact);
	}
	
	@Transactional
	public List<Contact> loadContacts() {
		return new ArrayList<Contact>(
				IteratorUtil.asCollection(
						template.findAll(Contact.class)));
	}
	
	@Transactional
	public Contact loadContact(Long id) {
		return template.findOne(id, Contact.class);
	}
	
	@Transactional
	public void removeContact(Contact contact) {
		template.delete(contact);
	}

}
