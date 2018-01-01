package timebank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(value = Interested.InterestedPK.class)
@Table(name = "interested")
public class Interested implements Serializable {

  @Id
  @Column(name = "idAdvert")
  private long idAdvert;

  @Id
  @Column(name = "interested")
  private String interested;

  public long getIdAdvert() {
    return idAdvert;
  }

  public void setIdAdvert(long idAdvert) {
    this.idAdvert = idAdvert;
  }

  public String getUsername() {
    return interested;
  }

  public void setUsername(String interested) {
    this.interested = interested;
  }

  static class InterestedPK implements Serializable {

    private long idAdvert;
    private String interested;

    public InterestedPK() {}

    public InterestedPK(long idAdvert, String interested) {
      this.idAdvert = idAdvert;
      this.interested = interested;
    }

    public long getIdAdvert() {
      return idAdvert;
    }

    public void setIdAdvert(long idAdvert) {
      this.idAdvert = idAdvert;
    }

    public String getInterested() {
      return interested;
    }

    public void setInterested(String interested) {
      this.interested = interested;
    }


    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof InterestedPK)) return false;
      InterestedPK that = (InterestedPK) o;
      return getIdAdvert() == that.getIdAdvert() && getInterested().equals(that.getInterested());
    }

    @Override
    public int hashCode() {
      int result = (int) (getIdAdvert() ^ (getIdAdvert() >>> 32));
      result = 31 * result + getInterested().hashCode();
      return result;
    }
  }

}
