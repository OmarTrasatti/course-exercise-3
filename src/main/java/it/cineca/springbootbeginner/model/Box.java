package it.cineca.springbootbeginner.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

@Entity
public class Box {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "author")
	@Length(max = 15)
	private String author;
	
	@Column(name = "lastUpdate")
	private Date lastUpdate;

	public Box(Long id, String name, @Length(max = 15) String author, Date lastUpdate) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.lastUpdate = lastUpdate;
	}
}
