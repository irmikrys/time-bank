package timebank.api

import org.springframework.http.HttpStatus
import spock.lang.Shared
import spock.lang.Stepwise
import spockmvc.RequestParams
import timebank.AbstractMvcSpec

@Stepwise
class EmailControllerSpec extends AbstractMvcSpec {

  @Shared
  String token

  def "user logs in to send email"() {
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

  def "user sends email"() {
    given:
    def email = [title: 'some title', email: 'bill@gates', content: 'some content']

    when:
    def result = post('/api/sendEmail', email, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
  }

}
