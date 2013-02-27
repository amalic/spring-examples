package org.amalic.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.amalic.domain.Contact;
import org.amalic.service.ContactService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactController {
	private static final Logger log = Logger.getLogger(ContactController.class);
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/")
	public String listContacts(Map<String, Object> map) {
		map.put("contact", new Contact());
		map.put("contactList", getAllContactsAsList());
		
		return "contact";
	}
	
	@RequestMapping("/{contactId}")
	public String editContact(@PathVariable("contactId") Long contactId, Map<String, Object> map) {
		Contact contact = contactService.loadContact(contactId);
		List<Contact> contacts = getAllContactsAsList();
		map.put("contact", contact);
		map.put("contactList", contacts);
		return "contact";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveContact(@ModelAttribute("Contact") Contact contact, BindingResult result) {
		if(null != contact.getManager() && null==contact.getManager().getId())
			contact.setManager(null);
		if(null == contact.getReportsTo())
			contact.setReportsTo(new HashSet<Contact>());
		if(null != contact.getManager() && !contact.getReportsTo().contains(contact.getManager()))
			contact.getReportsTo().add(contact.getManager());
		
		log.info("contact.getReportsTo()->" + contact.getReportsTo());
		
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
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "reportsTo", new CustomCollectionEditor(Set.class) {
			@Override
			protected Object convertElement(Object element) {
				String id = null;
				if(element instanceof String)
					id = (String) element;
				else if (element instanceof Long)
					id = ((Long)element).toString();
				return id != null ? contactService.loadContact(Long.decode(id)) : null;
			}
		});
	}
	
}
