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
    def credentials = [username: 'billgates', password: 'zy7x2DaP']

    when:
    def response = post('/api/session', credentials)

    then:
    response.status == HttpStatus.UNAUTHORIZED
  }

  def "good authentication"() {
    given:
    def credentials = [username: 'billgates', password: '2aNJn29r']

    when:
    def response = post('/api/session', credentials)
    token = response.json.token

    then:
    response.status == HttpStatus.OK
    response.json.username == 'billgates'
    token != null
  }

  def "get session"() {
    when:
    def response = get('/api/session', new RequestParams(authToken: token))

    then:
    response.status == HttpStatus.OK
    response.json.username == 'billgates'
  }

  def "delete session"() {
    when:
    def response = delete('/api/session', new RequestParams(authToken: token))

    then:
    response.status == HttpStatus.OK

    when:
    response = get('/api/session', new RequestParams(authToken: token))

    then:
    response.status == HttpStatus.OK
    response.content.isEmpty()
  }
}
