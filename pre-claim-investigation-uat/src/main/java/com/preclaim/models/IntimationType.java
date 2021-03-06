package com.preclaim.models;

public class IntimationType {

	private String intimationType;
	private String createdBy;
	private String updatedBy;
	private int status;
	private int intimationId;

	public IntimationType() {
		this.intimationType = "";
		this.createdBy = "";
		this.updatedBy = "";
		this.status = 0;
		this.intimationId = 0;

	}

	public String getIntimationType() {
		return intimationType;
	}

	public void setIntimationType(String intimationType) {
		this.intimationType = intimationType;
	}

	public int getIntimationId() {
		return intimationId;
	}

	public void setIntimationId(int intimationId) {
		this.intimationId = intimationId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IntimationType [intimationType=" + intimationType + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", status=" + status + ", intimationId=" + intimationId + "]";
	}

}
