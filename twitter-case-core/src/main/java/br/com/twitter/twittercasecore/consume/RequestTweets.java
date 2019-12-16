package br.com.twitter.twittercasecore.consume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class RequestTweets {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * {@literal}Metodo de servico GET para Tweets.
	 * 
	 * @param url
	 * @param token
	 * @return
	 */
	public HttpResponse<String> responseGet(String url, String token) {

		HttpResponse<String> response = null;

		Unirest.setTimeouts(10000L, 600000L);

		try {

			response = Unirest.get(url).header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).asString();

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}

		return response;
	}

}
