package br.com.twitter.twittercasecore.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.twitter.twittercasecore.model.TweetsModel;

@Repository
public interface TweetsRepo extends CrudRepository<TweetsModel, Long> {

	List<TweetsModel> findTop5ByOrderByFollowersDesc();

}
