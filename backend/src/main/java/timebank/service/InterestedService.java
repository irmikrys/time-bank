package timebank.service;

import timebank.model.Interested;

public interface InterestedService {

  Iterable<Interested> findAllByIdAdvert(long idAdvert);

  Iterable<Interested> findAllByInterested(String interested);

  Interested showInterest(long idAdvert, String username);

}
