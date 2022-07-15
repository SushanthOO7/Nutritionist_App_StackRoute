package com.project.model;

import java.util.List;
import javax.persistence.Column;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "food")
public class  Food{

	@Column(name = "fdcId")
	private Integer _id;
	private String description;
	private String dataType;
	private String publicationDate;
	private String ingredients;
	private String brandOwner;
	private String scientificName;
	private String brandedFoodCategory;
	
	private List<Nutrients> foodNutrients;
	
	public Food() {}

	public Food(Integer fdcId, String description, String dataType, String publicationDate, String ingredients,
			String brandOwner, String scientificName, String brandedFoodCategory, List<Nutrients> foodNutrients) {
		super();
		this._id = fdcId;
		this.description = description;
		this.dataType = dataType;
		this.publicationDate = publicationDate;
		this.ingredients = ingredients;
		this.brandOwner = brandOwner;
		this.scientificName = scientificName;
		this.brandedFoodCategory = brandedFoodCategory;
		this.foodNutrients = foodNutrients;
	}

	public Integer getFdcId() {
		return _id;
	}

	public void setFdcId(Integer fdcId) {
		this._id = fdcId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getBrandOwner() {
		return brandOwner;
	}

	public void setBrandOwner(String brandOwner) {
		this.brandOwner = brandOwner;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getBrandedFoodCategory() {
		return brandedFoodCategory;
	}

	public void setBrandedFoodCategory(String brandedFoodCategory) {
		this.brandedFoodCategory = brandedFoodCategory;
	}

	public List<Nutrients> getFoodNutrients() {
		return foodNutrients;
	}

	public void setFoodNutrients(List<Nutrients> foodNutrients) {
		this.foodNutrients = foodNutrients;
	}

	@Override
	public String toString() {
		return "Food [fdcId=" + _id + ", description=" + description + ", dataType=" + dataType + ", publicationDate="
				+ publicationDate + ", ingredients=" + ingredients + ", brandOwner=" + brandOwner + ", scientificName="
				+ scientificName + ", brandedFoodCategory=" + brandedFoodCategory + ", foodNutrients=" + foodNutrients
				+ "]";
	}
	
	

}
