package com.tarombo.sirait.model;

public class Generation {
	private String Id;
	private String FatherId;
	private String ChildsId;
	private String Description;
	
	public String getId() { return Id;}
	public void setId(String id) { Id = id;}
	public String getFatherId() { return FatherId;}
	public void setFatherId(String fatherId) { FatherId = fatherId;}
	public String getChildsId() { return ChildsId;}
	public void setChildsId(String childsId) { ChildsId = childsId;}
	public String getDescription() { return Description;}
	public void setDescription(String description) { Description = description;}

}
