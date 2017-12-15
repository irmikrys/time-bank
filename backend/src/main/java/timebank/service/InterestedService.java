package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import timebank.model.Interested;
import timebank.repository.InterestedRepository;

import java.util.Optional;

@Service("interestedService")
public class InterestedService {

  @Autowired
  @Qualifier("interestedRepository")
  private InterestedRepository interestedRepository;
  
  
  public Optional<Interested> findByIdAdvertAndInterested(long idAdvert, String interested) {
    return this.interestedRepository.findByIdAdvertAndInterested(idAdvert, interested);
  }

  public Iterable<Interested> findAllByIdAdvert(long idAdvert) {
    return this.interestedRepository.findAllByIdAdvert(idAdvert);
  }

  public Iterable<Interested> findAllByInterested(String interested) {
    return this.interestedRepository.findAllByInterested(interested);
  }

  // TODO - jaki typ? void?
  public Interested showInterest(long idAdvert, String username) {
    Interested interested = new Interested();
    interested.setIdAdvert(idAdvert);
    interested.setUsername(username);
    return this.interestedRepository.save(interested);
  }
  
  @Transactional
  public void stopShowingInterest(long idAdvert, String username) {
    this.interestedRepository.deleteByIdAdvertAndInterested(idAdvert, username);
  }
  
  @Transactional
  public void deleteInterestedInAdvert(long idAdvert) {
    this.interestedRepository.deleteByIdAdvert(idAdvert);
  }
}
