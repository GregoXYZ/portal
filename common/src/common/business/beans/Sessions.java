package common.business.beans;

// Generated 14-ene-2009 19:22:13 by Hibernate Tools 3.2.4.CR1

/**
 * Sessions generated by hbm2java
 */
public class Sessions implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9125673698501004584L;
	private Long sessionPk;
	private String sessionId;
	private String shareSessionId;
	private char validSession;
	private String address;
	private Integer maxInactive;
	private Long creationTime;
	private Long lastAccessedTime;
	private String appName;
	private byte[] sessionData;
	private Long userId;
	private Long closedTime;

	public Sessions() {
	}

	public Sessions(String sessionId, String shareSessionId, char validSession,
			int maxInactive, long creationTime, Long userId) {
		this.sessionId = sessionId;
		this.shareSessionId = shareSessionId;
		this.validSession = validSession;
		this.maxInactive = maxInactive;
		this.creationTime = creationTime;
		this.userId = userId;
	}

	public Sessions(String sessionId, String shareSessionId, char validSession,
			int maxInactive, long creationTime, Long lastAccessedTime,
			String appName, byte[] sessionData, Long userId) {
		this.sessionId = sessionId;
		this.shareSessionId = shareSessionId;
		this.validSession = validSession;
		this.maxInactive = maxInactive;
		this.creationTime = creationTime;
		this.lastAccessedTime = lastAccessedTime;
		this.appName = appName;
		this.sessionData = sessionData;
		this.userId = userId;
	}

	public Long getSessionPk() {
		return this.sessionPk;
	}

	public void setSessionPk(Long sessionPk) {
		this.sessionPk = sessionPk;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getShareSessionId() {
		return this.shareSessionId;
	}

	public void setShareSessionId(String shareSessionId) {
		this.shareSessionId = shareSessionId;
	}

	public char getValidSession() {
		return this.validSession;
	}

	public void setValidSession(char validSession) {
		this.validSession = validSession;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public int getMaxInactive() {
		return this.maxInactive;
	}

	public void setMaxInactive(Integer maxInactive) {
		this.maxInactive = maxInactive;
	}

	public Long getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public Long getLastAccessedTime() {
		return this.lastAccessedTime;
	}

	public void setLastAccessedTime(Long lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public byte[] getSessionData() {
		return this.sessionData;
	}

	public void setSessionData(byte[] sessionData) {
		this.sessionData = sessionData;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setClosedTime(Long closedTime) {
		this.closedTime = closedTime;
	}

	public Long getClosedTime() {
		return closedTime;
	}

}
