package ru.supplyphotos.data.answers.category;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Category{

	@SerializedName("data")
	@Expose
	private List<ItemCategory> itemCategoryList;

	public void setData(List<ItemCategory> data){
		this.itemCategoryList = data;
	}

	public List<ItemCategory> getListCategory(){
		return itemCategoryList;
	}

	@Override
 	public String toString(){
		return 
			"Category{" + 
			"data = '" + itemCategoryList + '\'' +
			"}";
		}
}