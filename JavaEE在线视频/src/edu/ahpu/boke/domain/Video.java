package edu.ahpu.boke.domain;

import java.sql.Timestamp;

/**
 * Video entity. @author MyEclipse Persistence Tools
 */

public class Video implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer channelId;
	private Integer userId;
	private String clientFileName;
	private String serverFileName;
	private String picFileName;
	private String title;
	private String tags;
	private String description;
	private Integer playCount;
	private Integer commentCount;
	private Integer goodCommentCount;
	private Integer badCommentCount;
	private Integer duration;
	private Timestamp uploadTime;
	private String status;

	// Constructors

	/** default constructor */
	public Video() {
	}

	/** full constructor */
	public Video(Integer channelId, Integer userId, String clientFileName,
			String serverFileName, String picFileName, String title,
			String tags, String description, Integer playCount,
			Integer commentCount, Integer goodCommentCount,
			Integer badCommentCount, Integer duration, Timestamp uploadTime,
			String status) {
		this.channelId = channelId;
		this.userId = userId;
		this.clientFileName = clientFileName;
		this.serverFileName = serverFileName;
		this.picFileName = picFileName;
		this.title = title;
		this.tags = tags;
		this.description = description;
		this.playCount = playCount;
		this.commentCount = commentCount;
		this.goodCommentCount = goodCommentCount;
		this.badCommentCount = badCommentCount;
		this.duration = duration;
		this.uploadTime = uploadTime;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getClientFileName() {
		return this.clientFileName;
	}

	public void setClientFileName(String clientFileName) {
		this.clientFileName = clientFileName;
	}

	public String getServerFileName() {
		return this.serverFileName;
	}

	public void setServerFileName(String serverFileName) {
		this.serverFileName = serverFileName;
	}

	public String getPicFileName() {
		return this.picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPlayCount() {
		return this.playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getGoodCommentCount() {
		return this.goodCommentCount;
	}

	public void setGoodCommentCount(Integer goodCommentCount) {
		this.goodCommentCount = goodCommentCount;
	}

	public Integer getBadCommentCount() {
		return this.badCommentCount;
	}

	public void setBadCommentCount(Integer badCommentCount) {
		this.badCommentCount = badCommentCount;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}