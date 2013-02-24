package org.amalic.orm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity @Data @EqualsAndHashCode(of={"id"}) 
public class  Contact {
	@Id @GeneratedValue	
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
}
