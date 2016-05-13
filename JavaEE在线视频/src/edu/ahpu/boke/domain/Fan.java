package edu.ahpu.boke.domain;

/**
 * Fan entity. @author MyEclipse Persistence Tools
 */

public class Fan implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer listenerId;
	private Integer hostId;

	// Constructors

	/** default constructor */
	public Fan() {
	}

	/** full constructor */
	public Fan(Integer listenerId, Integer hostId) {
		this.listenerId = listenerId;
		this.hostId = hostId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getListenerId() {
		return this.listenerId;
	}

	public void setListenerId(Integer listenerId) {
		this.listenerId = listenerId;
	}

	public Integer getHostId() {
		return this.hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

}