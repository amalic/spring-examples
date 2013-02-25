package org.amalic.service;

import java.util.List;

import org.amalic.domain.Contact;
import org.amalic.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Transactional
	public void addContact(Contact contact) {
		contactRepository.addContact(contact);
	}

	@Transactional
	public List<Contact> loadContacts() {
		return contactRepository.loadContacts();
	}

	@Transactional
	public Contact loadContact(Long id) {
		return contactRepository.loadContact(id);
	}

	@Transactional
	public void deleteContact(Long id) {
		contactRepository.removeContact(contactRepository.loadContact(id));
	}
	
}
