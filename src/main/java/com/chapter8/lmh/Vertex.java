package com.chapter8.lmh;

public class Vertex {
	private int id;
	private String name;
	private double weight;
	
	public Vertex(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Vertex(int id, String name, double weight) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
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
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
}
