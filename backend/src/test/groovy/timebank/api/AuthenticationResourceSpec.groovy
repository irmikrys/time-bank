package timebank.api

import org.springframework.http.HttpStatus
import timebank.AbstractMvcSpec
import spock.lang.Shared
import spock.lang.Stepwise
import spockmvc.RequestParams

@Stepwise
class AuthenticationResourceSpec extends AbstractMvcSpec {

  @Shared
  String token

  def "bad authentication"() {
    given:
    def credentials = [username: 'user', password: 'badpassword']

    when:
    def res = post('/api/session', credentials)

    then:
    res.status == HttpStatus.UNAUTHORIZED
  }

  def "good authentication"() {
    given:
    def credentials = [username: 'test', password: 'Password1']

    when:
    def res = post('/api/session', credentials)
    token = res.json.token

    then:
    res.status == HttpStatus.OK
    res.json.username == 'test'
    token != null
  }

  def "get session"() {
    when:
    def res = get('/api/session', new RequestParams(authToken: token))

    then:
    res.status == HttpStatus.OK
    res.json.username == 'test'
  }

  def "delete session"() {
    when:
    def res = delete('/api/session', new RequestParams(authToken: token))

    then:
    res.status == HttpStatus.OK

    when:
    res = get('/api/session', new RequestParams(authToken: token))

    then:
    res.status == HttpStatus.OK
    res.content.isEmpty()
  }
}
