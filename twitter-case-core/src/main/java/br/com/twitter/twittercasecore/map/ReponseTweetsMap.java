package br.com.twitter.twittercasecore.map;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;

import br.com.twitter.twittercasecore.entities.TweetsEntity;


@Component
public class ReponseTweetsMap {
	private final static Logger logger = LoggerFactory.getLogger(ReponseTweetsMap.class);

	/**
	 * {@literal}Metodo que mapeia os campos do response.
	 * 
	 * @param response
	 * @return
	 */
	public static TweetsEntity tweetsEntityMap(HttpResponse<String> response) {
		TweetsEntity tweetsEntityMap = new TweetsEntity();

		ObjectMapper getTweetsEntity = new ObjectMapper();
		try {
			tweetsEntityMap = getTweetsEntity.readValue(response.getBody().toString(), TweetsEntity.class);
		} catch (JsonParseException e) {
			logger.error(e.getLocalizedMessage());
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return tweetsEntityMap;
	}

	public static TweetsEntity tweetsEntityMapList(HttpResponse<String> response) {
		TweetsEntity tweetsEntityList = new TweetsEntity();

		ObjectMapper getTweetsEntityList = new ObjectMapper();
		try {
			tweetsEntityList = getTweetsEntityList.readValue(response.getBody().toString(),
					new TypeReference<TweetsEntity>() {
					});
		} catch (JsonParseException e) {
			logger.error(e.getLocalizedMessage());
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return tweetsEntityList;
	}

}
