package com.datdev.model;

import java.sql.Date;

public class CategoryModel  extends AbstractModel<CategoryModel>{
	private String name;
	private String code;


	public CategoryModel() {
		super();
	}

	public CategoryModel(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}





}
