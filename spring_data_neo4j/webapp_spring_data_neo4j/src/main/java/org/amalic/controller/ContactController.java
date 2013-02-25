package org.amalic.controller;

import java.util.List;
import java.util.Map;

import org.amalic.domain.Contact;
import org.amalic.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@RequestMapping("/")
	public String listContacts(Map<String, Object> map) {
		map.put("contact", new Contact());
		map.put("contactList", getAllContactsAsList());
		
		return "contact";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addContact(@ModelAttribute("Contact") Contact contact, BindingResult result) {
		contactRepository.addContact(contact);
		
		return "redirect:/";
	}
	
	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId") Long contactId) {
		contactRepository.removeContact(contactRepository.loadContact(contactId));
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/rest/contact/{contactId}", method=RequestMethod.GET)
	public @ResponseBody Contact getContact(@PathVariable("contactId") Long contactId) {
		Contact contact = contactRepository.loadContact(contactId);
		
		return contact;
	}
	
	@RequestMapping(value="/rest/contacts", method=RequestMethod.GET)
	public @ResponseBody List<Contact> getAllContactsAsList() {
		return contactRepository.loadContacts();
	}
	
}
