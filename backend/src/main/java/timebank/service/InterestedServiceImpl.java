package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import timebank.model.Interested;
import timebank.repository.InterestedRepository;

@Service("interestedService")
public class InterestedServiceImpl implements InterestedService {

  @Autowired
  @Qualifier("interestedRepository")
  private InterestedRepository interestedRepository;

  @Override
  public Iterable<Interested> findAllByIdAdvert(long idAdvert) {
    return interestedRepository.findAllByIdAdvert(idAdvert);
  }

  @Override
  public Iterable<Interested> findAllByInterested(String interested) {
    return interestedRepository.findAllByInterested(interested);
  }

  @Override
  public Interested showInterest(long idAdvert, String username) {
    Interested interested = new Interested();
    interested.setIdAdvert(idAdvert);
    interested.setUsername(username);
    return interestedRepository.save(interested);
  }
}
