package br.com.twitter.twittercasecore.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;

import br.com.twitter.twittercasecore.consume.RequestTweets;
import br.com.twitter.twittercasecore.entities.TweetsEntity;
import br.com.twitter.twittercasecore.entities.TweetsStatuses;
import br.com.twitter.twittercasecore.map.ReponseTweetsMap;
import br.com.twitter.twittercasecore.model.TweetsModel;
import br.com.twitter.twittercasecore.repositories.TweetsRepo;
import br.com.twitter.twittercasecore.utils.Util;

@Service
public class TweetsImpl {

	@Autowired
	private TweetsRepo tweetsRepo;

	private Util util = new Util();
	private Logger logger = LoggerFactory.getLogger(TweetsImpl.class);
	
	
	/**
	 * {@literal} Metodo buscar tweets pela hashtag
	 * 
	 * @param hashtag
	 * @param token
	 */

	public TweetsEntity getTweetsByHashtag(String hashtag, String token) {
		
		logger.info("####### VERIFICANDO HASHTAG VALIDA ########");

		if(util.mapHashtag(hashtag)) {
			List<TweetsStatuses> tweets = new ArrayList<TweetsStatuses>();
			logger.info("####### BUSCAR TWEETS BY HASHTAG ########");

			String url = "";
			url = "https://api.twitter.com/1.1/search/tweets.json?q=%23" + hashtag + "&result_type=recent";

			TweetsEntity tweetsEntity = new TweetsEntity();
			RequestTweets requestTweets = new RequestTweets();
			HttpResponse<String> response = requestTweets.responseGet(url, token);

			if (response.getStatus() == 200) {

				logger.info(url);
				logger.info("GET DONE!!!");

				tweetsEntity = ReponseTweetsMap.tweetsEntityMapList(response);

				tweets = tweetsEntity.getStatuses();

				for (TweetsStatuses tweetstatuses : tweets) {
					TweetsModel tweetsModel = new TweetsModel();
					tweetsModel.setCreated_at(tweetstatuses.getCreated_at());
					tweetsModel.setText(tweetstatuses.getText());
					tweetsModel.setFollowers(Integer.parseInt(tweetstatuses.getUser().getFollowers_count()));
					tweetsModel.setName(tweetstatuses.getUser().getName());

					addNovosTweets(tweetsModel);
				}
				
			} else {

				logger.info(url);
				logger.info(response.getBody());

			}

			logger.info("HTTP CODE: " + response.getStatus());
			return tweetsEntity;
			
		} else {
			
			logger.info("####### HASHTAG INVALIDA ########");	
			return null;
		}
	}

	/**
	 * {@literal} Metodo para salvar tweets
	 * 
	 * @param tweetsModel
	 */

	public TweetsModel addNovosTweets(TweetsModel tweetsModel) {
		logger.info("Gravando novos tweets no banco...");

		try {
			TweetsModel returnValue = tweetsRepo.save(tweetsModel);
			logger.info("Tweets adicionados com sucesso");

			return returnValue;
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateKeyException(e.getCause().getCause().getMessage());
		}

	}
	
	/**
	 * {@literal} Metodo buscar top 5 tweets da base por followers
	 * 
	 * @param none
	 */
	
	public List<TweetsModel> topTweets(){
		logger.info("####### BUSCAR TOP 5 TWEETS POR FOLLOWERS ########");
		List<TweetsModel> topTweetsFollow = tweetsRepo.findTop5ByOrderByFollowersDesc();
		if (!topTweetsFollow.isEmpty()) {
			logger.info("Todos os tweets encontrado com sucesso");
		} else {
			logger.info("Busca retornou vazia");
		}
		return topTweetsFollow;
		
	}

}
