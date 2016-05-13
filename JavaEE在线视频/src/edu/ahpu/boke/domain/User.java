package edu.ahpu.boke.domain;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String password;
	private Integer facePicId;
	private Timestamp lastLoginTime;
	private Integer visitCount;
	private Integer totalPlayCount;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String name, String password, Integer facePicId,
			Timestamp lastLoginTime, Integer visitCount, Integer totalPlayCount) {
		this.name = name;
		this.password = password;
		this.facePicId = facePicId;
		this.lastLoginTime = lastLoginTime;
		this.visitCount = visitCount;
		this.totalPlayCount = totalPlayCount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getFacePicId() {
		return this.facePicId;
	}

	public void setFacePicId(Integer facePicId) {
		this.facePicId = facePicId;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getVisitCount() {
		return this.visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public Integer getTotalPlayCount() {
		return this.totalPlayCount;
	}

	public void setTotalPlayCount(Integer totalPlayCount) {
		this.totalPlayCount = totalPlayCount;
	}

}