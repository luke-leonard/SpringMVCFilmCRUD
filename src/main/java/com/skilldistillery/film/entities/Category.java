package com.skilldistillery.film.entities;

public class Category {
	// F I E L D S
	private int id;
	private String name;
	
	// C O N S T R U C T O R S
	public Category() {
		super();
	}
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	// G E T T E R S _ A N D _ S E T T E R S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
