package com.tarombo.sirait.model;


public class SiraitFamily {
	
	private String generationId;
	private String name;
	private int numberOfSons;
	private String description;
	private boolean isSelected = false;
	
	public String getGenerationId() { return generationId;}
	public void setGenerationId(String generationId) { this.generationId = generationId;}
	public String getName() { return name;}
	public void setName(String name) { this.name = name;}
	public int getNumberOfSons() { return numberOfSons;}
	public void setNumberOfSons(int numberOfSons) { this.numberOfSons = numberOfSons;}
	public String getDescription() { return description;}
	public void setDescription(String description) { this.description = description;}
	public boolean isSelected() {return isSelected;}
	public void setSelected(boolean isSelected) { this.isSelected = isSelected;}
	
}
