package org.amalic.domain;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity @Data @EqualsAndHashCode(of={"id"}) @ToString(of={"id","userName"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Contact {
	public static final String INDEX_USERNAME = "contact.userName"; 
	@GraphId
	Long id;
	@Indexed(indexType=IndexType.FULLTEXT, indexName=INDEX_USERNAME)
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String country;
	@RelatedTo(type="IS_MANAGER_OF", direction = Direction.INCOMING)
	private Contact manager;
	@RelatedTo(type="REPORTS_TO")
	private Set<Contact> reportsTo;
	@RelatedTo(type="REPORTS_TO", direction = Direction.INCOMING)
	private Set<Contact> directReports;
}
