package timebank.api

import org.springframework.http.HttpStatus
import spock.lang.Shared
import spock.lang.Stepwise
import spockmvc.RequestParams
import timebank.AbstractMvcSpec

@Stepwise
class AdvertControllerSpec extends AbstractMvcSpec {

  @Shared
  String token

  def "user logs in to add new advert"() {
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

  def "create advert with correct data"() {
    given:
    def request = [
      type: 'SEEK',
      title: 'Zabawa z chomikiem',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory: '1',
      value: '4',
      location : [
        description : 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude : '47.6062095',
        longitude : '-122.3320708'
      ]
    ]

    when:
    def result = post('/api/advert', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
  }

  def "create advert with negative value"() {
    given:
    def request = [
      type: 'SEEK',
      title: 'Zabawa z chomikiem',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory: '1',
      value: '-4',
      location : [
        description : 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude : '47.6062095',
        longitude : '-122.3320708'
      ]
    ]

    when:
    def result = post('/api/advert', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "create advert with too big value"() {
    given:
    def request = [
      type: 'SEEK',
      title: 'Zabawa z chomikiem',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory: '1',
      value: '1001',
      location : [
        description : 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude : '47.6062095',
        longitude : '-122.3320708'
      ]
    ]

    when:
    def result = post('/api/advert', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "create advert without required fields"() {
    given:
    def request = [
      type: 'SEEK',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory: '1',
      value: '1001',
      location : [
        description : 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude : '47.6062095',
        longitude : '-122.3320708'
      ]
    ]

    when:
    def result = post('/api/advert', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

}
