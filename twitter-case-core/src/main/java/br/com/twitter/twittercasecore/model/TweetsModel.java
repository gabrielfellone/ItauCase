package br.com.twitter.twittercasecore.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "TweetsModel")
public class TweetsModel {

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 255)
	private String created_at;

	@Column(length = 500)
	private String text;

	@Column(length = 255)
	private String name;

	@Column(length = 255)
	private int followers;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

}
