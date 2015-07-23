package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MensagemTopico {

	public MensagemTopico(long id, long discussion, long parent, long userId, String subject, String message) {
		this.id = id;
		this.discussion = discussion;
		this.parent = parent;
		this.userId = userId;
		this.subject = subject;
		this.message = message;
	}

	private long id;
	private long discussion;
	private long parent;
	private long userId;
	private String subject;
	private String message;
	
	public MensagemTopico(){
		
	}
	
	public MensagemTopico(long discussion, long parent, long userId, String subject, String message) {
		this.discussion = discussion;
		this.parent = parent;
		this.userId = userId;
		this.subject = subject;
		this.message = message;
	}
	public long getDiscussion() {
		return discussion;
	}
	public void setDiscussion(long discussion) {
		this.discussion = discussion;
	}
	public long getParent() {
		return parent;
	}
	public void setParent(long parent) {
		this.parent = parent;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
