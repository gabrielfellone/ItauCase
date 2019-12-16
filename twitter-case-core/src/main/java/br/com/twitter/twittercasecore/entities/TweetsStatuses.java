package br.com.twitter.twittercasecore.entities;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetsStatuses {
	
	@Autowired
	private TweetsUser user;
	
	@JsonProperty("created_at")
	private String created_at;
	@JsonProperty("text")
	private String text;
	

	public TweetsUser getUser() {
		return user;
	}

	public void setUser(TweetsUser user) {
		this.user = user;
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

	@Override
	public String toString() {
		return "Tweetstatuses [created_at=" + created_at + ", text=" + text + ", user=" + user + "]";
	}

}
