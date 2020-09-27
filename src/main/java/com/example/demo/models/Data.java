package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Data implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1858486523860521383L;
	@Id
	@GeneratedValue
	private int id;
    private String name;
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Data(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
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
