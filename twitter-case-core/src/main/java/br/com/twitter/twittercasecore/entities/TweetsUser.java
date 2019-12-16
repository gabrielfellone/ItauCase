package br.com.twitter.twittercasecore.entities;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetsUser {

	@JsonProperty("name")
	private String name;
	@JsonProperty("followers_count")
	private String followers_count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(String followers_count) {
		this.followers_count = followers_count;
	}

	@Override
	public String toString() {
		return "Tweetstatuses [name=" + name + ", followers_count=" + followers_count + "]";
	}

}
