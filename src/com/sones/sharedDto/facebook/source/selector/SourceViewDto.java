package com.sones.sharedDto.facebook.source.selector;

public class SourceViewDto
{
	private String appUserId;
	private String sourceId;
	private String sourceName;
	private String sourceType;
	
	public SourceViewDto()
	{}

	/**
	 * @param appUserId
	 * @param sourceId
	 * @param sourceName
	 * @param sourceType
	 */
	public SourceViewDto(String appUserId, String sourceId, String sourceName,
			String sourceType) {
		super();
		this.appUserId = appUserId;
		this.sourceId = sourceId;
		this.sourceName = sourceName;
		this.sourceType = sourceType;
	}

	/**
	 * @return the appUserId
	 */
	public String getAppUserId() {
		return appUserId;
	}

	/**
	 * @param appUserId the appUserId to set
	 */
	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}

	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
}
