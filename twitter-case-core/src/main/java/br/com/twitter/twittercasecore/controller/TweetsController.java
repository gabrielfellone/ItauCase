package br.com.twitter.twittercasecore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.twitter.twittercasecore.entities.TweetsEntity;
import br.com.twitter.twittercasecore.impl.TweetsImpl;
import br.com.twitter.twittercasecore.model.TweetsModel;



@RestController
@RequestMapping("/tweets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TweetsController {

	@Autowired
	private TweetsImpl tweetsImpl;

	// Pesquisa recent tweets por hashtag e salva na base
	@GetMapping("/hashtag/{hashtag}")
	public TweetsEntity getTweetsByHashtag(@PathVariable String hashtag) {
		TweetsEntity tweetsEntity = null;
		String token = "AAAAAAAAAAAAAAAAAAAAAArSBAEAAAAAbvQfSO%2Bl2YC7P4hp7EB4FoLAMdY%3DGtCc4jc4avm7GyUZX8vXmfKoTb70rJlitE5Gu7U1SqXB3Ie5wv";
		try {
			tweetsEntity = tweetsImpl.getTweetsByHashtag(hashtag, token);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tweetsEntity;
	}
	
	// Pesquisa top 5 tweets da base
	@GetMapping
	public List<TweetsModel> getTopTweets() {
		List<TweetsModel> topTweets = null;
		try {
			topTweets = tweetsImpl.topTweets();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return topTweets;
	}

}
