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

  @Shared
  String token2

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

  def "another user logs in to add new advert"() {
    given:
    def credentials = [username: 'stevejobs', password: 'F7zKwGg2']

    when:
    def response = post('/api/session', credentials)
    token2 = response.json.token

    then:
    response.status == HttpStatus.OK
    response.json.username == 'stevejobs'
    token2 != null
  }

  def "see categories"() {
    when:
    def result = get('/api/categories')

    then:
    result.status == HttpStatus.OK
    result.json.size == 8
  }

  def "see existing adverts"() {
    when:
    def result = get('/api/adverts')

    then:
    result.status == HttpStatus.OK
  }

  def "see non-existing advert"() {
    when:
    def result = get('/api/advert/30', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "see advert with non-existing location"() {
    when:
    def result = get('/api/advert/1', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "see advert with non-existing owner"() {
    when:
    def result = get('/api/advert/4', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "see existing advert"() {
    when:
    def result = get('/api/advert/2', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
  }


  def "create advert with correct data"() {
    given:
    def request = [
      type       : 'SEEK',
      title      : 'Zabawa z chomikiem',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory : '1',
      value      : '4',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
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
      type       : 'SEEK',
      title      : 'Zabawa z chomikiem',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory : '1',
      value      : '-4',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
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
      type       : 'SEEK',
      title      : 'Zabawa z chomikiem',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory : '1',
      value      : '1001',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
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
      type       : 'SEEK',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory : '1',
      value      : '1001',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
      ]
    ]

    when:
    def result = post('/api/advert', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "see created adverts"() {
    when:
    def result = get('/api/createdAdverts', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
    result.json.size == 3
  }

  def "update advert with correct data"() {
    given:
    def request = [
      type       : 'SEEK',
      title      : 'Zabawa z chomiczkiem',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory : '1',
      value      : '4',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
      ]
    ]

    when:
    def result = put('/api/updateAdvert/3', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
  }

  def "update not owned advert"() {
    given:
    def request = [
      type       : 'SEEK',
      title      : 'Zabawa z chomiczkiem',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory : '1',
      value      : '4',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
      ]
    ]

    when:
    def result = put('/api/updateAdvert/3', request, new RequestParams(authToken: token2))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "update non-existing advert"() {
    given:
    def request = [
      type       : 'SEEK',
      title      : 'Zabawa z chomiczkiem',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory : '1',
      value      : '4',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
      ]
    ]

    when:
    def result = put('/api/updateAdvert/30', request, new RequestParams(authToken: token2))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "update advert with non-existing location"() {
    given:
    def request = [
      type       : 'SEEK',
      title      : 'Zabawa z chomiczkiem',
      description: 'Szukam kogos kto pobawi sie chwile z moim chomikiem, uwielbia zabawe!',
      idCategory : '1',
      value      : '4',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
      ]
    ]

    when:
    def result = put('/api/updateAdvert/1', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "switch interest of someone's advert to interested"() {
    when:
    def result = post('/api/advert/switchInterest/3', null, new RequestParams(authToken: token2))

    then:
    result.status == HttpStatus.OK
  }

  def "switch interest of someone's advert to non-interested"() {
    when:
    def result = post('/api/advert/switchInterest/3', null, new RequestParams(authToken: token2))

    then:
    result.status == HttpStatus.OK
  }

  def "switch interest of someone's advert to interested again"() {
    when:
    def result = post('/api/advert/switchInterest/3', null, new RequestParams(authToken: token2))

    then:
    result.status == HttpStatus.OK
  }

  def "switch interest of non-existing advert"() {
    when:
    def result = post('/api/advert/switchInterest/20', null, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "switch interest of owned advert"() {
    when:
    def result = post('/api/advert/switchInterest/3', null, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "see interesting adverts"() {
    when:
    def result = get('/api/interestingAdverts', new RequestParams(authToken: token2))

    then:
    result.status == HttpStatus.OK
    result.json.size == 1
  }

  def "delete final contractor when one is undefined"() {
    when:
    def result = delete('/api/advert/deleteContractor/3', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "choose correct contractor"() {
    when:
    def result = post('/api/advert/chooseContractor?idAdvert=3&contractor=stevejobs', null,
      new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
  }

  def "choose contractor not interested with advert"() {
    when:
    def result = post('/api/advert/chooseContractor?idAdvert=1&contractor=markzuckerberg', null,
      new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "choose contractor of inactive advert"() {
    when:
    def result = post('/api/advert/chooseContractor?idAdvert=3&contractor=stevejobs', null,
      new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "choose contractor of non-existing advert"() {
    when:
    def result = post('/api/advert/chooseContractor?idAdvert=20&contractor=stevejobs', null,
      new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "choose contractor of not owned advert"() {
    when:
    def result = post('/api/advert/chooseContractor?idAdvert=2&contractor=stevejobs', null,
      new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "delete final contractor of non-existing advert"() {
    when:
    def result = delete('/api/advert/deleteContractor/30', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "delete final contractor of not owned advert"() {
    when:
    def result = delete('/api/advert/deleteContractor/2', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "delete final contractor"() {
    when:
    def result = delete('/api/advert/deleteContractor/3', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
  }

  def "finalize transaction of advert with no contractor"() {
    when:
    def result = post('/api/advert/finalize/3', null, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "choose contractor after this one was deleted"() {
    when:
    def result = post('/api/advert/chooseContractor?idAdvert=3&contractor=stevejobs', null,
      new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
  }

  def "finalize transaction correctly"() {
    when:
    def result = post('/api/advert/finalize/3', null, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
  }

  def "finalize transaction of non-existing advert"() {
    when:
    def result = post('/api/advert/finalize/30', null, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "finalize transaction of not owned advert"() {
    when:
    def result = post('/api/advert/finalize/2', null, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "see archived adverts"() {
    when:
    def result = get('/api/archive', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
    result.json.size == 1
  }

  def "delete non-existing advert"() {
    when:
    def result = delete('/api/deleteAdvert/20', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "delete not owned advert"() {
    when:
    def result = delete('/api/deleteAdvert/2', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "delete owned advert"() {
    when:
    def result = delete('/api/deleteAdvert/2', new RequestParams(authToken: token2))

    then:
    result.status == HttpStatus.OK
  }

}
