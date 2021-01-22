package com.commons.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2759124147333816144L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=30)
	private String name;
	
	//To mark as a bidirectional relationship
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private List<User> users;

}