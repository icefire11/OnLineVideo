package edu.ahpu.boke.domain;

/**
 * Face entity. @author MyEclipse Persistence Tools
 */

public class Face implements java.io.Serializable {

	// Fields

	private Integer id;
	private String picFileName;

	// Constructors

	/** default constructor */
	public Face() {
	}

	/** full constructor */
	public Face(String picFileName) {
		this.picFileName = picFileName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPicFileName() {
		return this.picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

}