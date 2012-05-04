package com.sones.sharedDto.facebook.source.selector;

public class SourceCreateDto 
{
	private String applicationUserId;
	private String sourceId;
	private String sourceType;
	
	public SourceCreateDto(String applicationUserId, String sourceId, String sourceType)
	{
		this.setApplicationUserId(applicationUserId);
		this.setSourceId(sourceId);
		this.setSourceType(sourceType);
	}

	/**
	 * @param applicationUserId the applicationUserId to set
	 */
	public void setApplicationUserId(String applicationUserId) {
		this.applicationUserId = applicationUserId;
	}

	/**
	 * @return the applicationUserId
	 */
	public String getApplicationUserId() {
		return applicationUserId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}
}
