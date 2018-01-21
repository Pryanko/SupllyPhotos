package ru.supplyphotos.data.answers.category;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ItemCategory {

	@SerializedName("image480")
	@Expose
	private String image480;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("description")
	@Expose
	private String description;

	@SerializedName("id")
	@Expose
	private int id;

	@SerializedName("image1024")
	@Expose
	private String image1024;

	@SerializedName("type")
	@Expose
	private String type;

	public void setImage480(String image480){
		this.image480 = image480;
	}

	public String getImage480(){
		return image480;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setImage1024(String image1024){
		this.image1024 = image1024;
	}

	public String getImage1024(){
		return image1024;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"ItemCategory{" +
			"image480 = '" + image480 + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",image1024 = '" + image1024 + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}