package br.com.twitter.twittercasecore.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class TweetsEntity {

	@JsonProperty("statuses")
	private List<TweetsStatuses> statuses = new ArrayList<TweetsStatuses>();

	public List<TweetsStatuses> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<TweetsStatuses> statuses) {
		this.statuses = statuses;
	}

	@Override
	public String toString() {
		return "TweetsEntity [statuses=" + statuses + "]";
	}

}
