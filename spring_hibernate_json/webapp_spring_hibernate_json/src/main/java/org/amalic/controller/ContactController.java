package org.amalic.controller;

import java.util.List;
import java.util.Map;

import org.amalic.orm.model.Contact;
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
	
	@RequestMapping("/index")
	public String listContacts(Map<String, Object> map) {
		map.put("contact", new Contact());
		map.put("contactList", contactService.listContact());
		
		return "contact";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addContact(@ModelAttribute("Contact") Contact contact, BindingResult result) {
		contactService.addContact(contact);
		return "redirect:/index";
	}
	
	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId") Integer contactId) {
		contactService.removeContact(contactId);
		return "redirect:/index";
	}
	
	@RequestMapping(value="/rest/contact/{contactId}", method=RequestMethod.GET)
	public @ResponseBody Contact contactAsJson(@PathVariable("contactId") Integer contactId) {
		Contact contact = contactService.loadContact(contactId);
		return contact;
	}
	
	@RequestMapping(value="/rest/contacts", method=RequestMethod.GET)
	public @ResponseBody List<Contact> contactAsJson() {
		return contactService.listContact();
	}
	
}
