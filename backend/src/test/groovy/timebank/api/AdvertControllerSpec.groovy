package timebank.api

import org.springframework.http.HttpStatus
import spock.lang.Stepwise
import timebank.AbstractMvcSpec

@Stepwise
class AdvertControllerSpec extends AbstractMvcSpec {

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
    def result = post('/api/advert', request)

    then:
    result.status == HttpStatus.OK
  }

}
