package org.amalic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.amalic.domain.Contact;
import org.amalic.service.ContactService;
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
	private ContactService contactService;
	
	@RequestMapping("/")
	public String listContacts(Map<String, Object> map) {
		map.put("contact", new Contact());

		List<Contact> contactList = getAllContactsAsList();
		
		List<Contact> managerList = new ArrayList<>(contactList);
		managerList.add(0, new Contact());
		
		map.put("contactList", getAllContactsAsList());
		map.put("managerList", managerList);
		
		return "contact";
	}
	
	@RequestMapping("/{contactId}")
	public String editContact(@PathVariable("contactId") Long contactId, Map<String, Object> map) {
		Contact contact = contactService.loadContact(contactId);
		
		List<Contact> contactList = getAllContactsAsList();
		
		List<Contact> managerList = new ArrayList<>(contactList);
		managerList.remove(contact);
		managerList.add(0, new Contact());
		
		map.put("contact", contact);
		map.put("contactList", getAllContactsAsList());
		map.put("managerList", managerList);
		return "contact";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveContact(@ModelAttribute("Contact") Contact contact, BindingResult result) {
//		if(null!=contact.getManager() && null!=contact.getManager().getId())
//			contact.setManager(contactService.loadContact(contact.getManager().getId()));
		contactService.saveContact(contact);
		
		return "redirect:/";
	}
	
	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId") Long contactId) {
		contactService.deleteContact(contactId);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/rest/contact/{contactId}", method=RequestMethod.GET)
	public @ResponseBody Contact getContact(@PathVariable("contactId") Long contactId) {
		Contact contact = contactService.loadContact(contactId);
		
		return contact;
	}
	
	@RequestMapping(value="/rest/contacts", method=RequestMethod.GET)
	public @ResponseBody List<Contact> getAllContactsAsList() {
		return contactService.loadContacts();
	}
	
}
