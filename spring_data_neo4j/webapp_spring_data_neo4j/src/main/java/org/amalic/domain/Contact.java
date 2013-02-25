package org.amalic.domain;

import lombok.Data;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity @Data
public class Contact {
	public static final String INDEX_ID = "contact.id"; 
	@GraphId
	Long id; 
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String country;
}
