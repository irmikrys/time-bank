package timebank.api

import org.springframework.http.HttpStatus
import org.springframework.security.test.context.support.WithMockUser
import spock.lang.Stepwise
import spockmvc.RequestParams
import timebank.AbstractMvcSpec

@Stepwise
class UserControllerSpec extends AbstractMvcSpec {

  def "create account with correct data"() {
    given:
    def request = [
      username: 'dennisritchie',
      password: 'TWkJb8ZB',
      email: 'dennisritchie@test.com',
      firstName: 'Dennis',
      lastName: 'Ritchie',
      location : [
        description : 'Bronxville, Nowy Jork, Stany Zjednoczone',
        latitude : '40.93815439999999',
        longitude : '-73.8320784'
      ]
    ]

    when:
    def result = post('/api/register', request)

    then:
    result.status == HttpStatus.OK
  }

  def "create account with username that already exists"() {
    given:
    def request = [
      username: 'dennisritchie',
      password: 'TWkJb8ZB',
      email: 'dennisritchietest@test.com',
      firstName: 'Dennis',
      lastName: 'Ritchie',
      location : [
        description : 'Bronxville, Nowy Jork, Stany Zjednoczone',
        latitude : '40.93815439999999',
        longitude : '-73.8320784'
      ]
    ]

    when:
    def result = post('/api/register', request)

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "create account with email that already exists"() {
    given:
    def request = [
      username: 'dennisritchietest',
      password: 'TWkJb8ZB',
      email: 'dennisritchie@test.com',
      firstName: 'Dennis',
      lastName: 'Ritchie',
      location : [
        description : 'Bronxville, Nowy Jork, Stany Zjednoczone',
        latitude : '40.93815439999999',
        longitude : '-73.8320784'
      ]
    ]

    when:
    def result = post('/api/register', request)

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "create account without required fields"() {
    given:
    def request = [
      username: 'dennisritchie',
      password: 'TWkJb8ZB',
      email: 'dennisritchie@test.com',
      lastName: 'Ritchie',
      location : [
        latitude : '40.93815439999999',
        longitude : '-73.8320784'
      ]
    ]

    when:
    def result = post('/api/register', request)

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  @WithMockUser
  def "get list of all users"() {

    when:
    def result = get('/api/users')

    then:
    result.status == HttpStatus.OK
    result.json.size == 6

  }

  @WithMockUser
  def "get information about user profile"() {

    when:
    def result = get('/api/profile/dennisritchie')

    then:
    result.status == HttpStatus.OK
    result.json.user.username == "dennisritchie"
    result.json.user.email == "dennisritchie@test.com"
    result.json.user.firstName == "Dennis"
    result.json.user.lastName == "Ritchie"
    result.json.user.role == "USER"
    result.json.user.photo == null
    result.json.location.description == "Bronxville, Nowy Jork, Stany Zjednoczone"
    result.json.location.latitude == 40.93815439999999
    result.json.location.longitude == -73.8320784
    result.json.account.amount == 0

  }

  @WithMockUser
  def "get information about user profile with non-existing username"() {

    when:
    def result = get('/api/profile/nonexisting')

    then:
    result.status == HttpStatus.BAD_REQUEST

  }

  @WithMockUser
  def "get information about user profile with non-existing location"() {

    when:
    def result = get('/api/profile/usernolocation')

    then:
    result.status == HttpStatus.BAD_REQUEST

  }

  def "get information about user profile by username from session"() {
    given:
    def credentials = [username: 'dennisritchie', password: 'TWkJb8ZB']
    def response = post('/api/session', credentials)
    def token = response.json.token

    when:
    def result = get('/api/profile', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
    result.json.user.username == "dennisritchie"
    result.json.user.email == "dennisritchie@test.com"
    result.json.user.firstName == "Dennis"
    result.json.user.lastName == "Ritchie"
    result.json.user.role == "USER"
    result.json.user.photo == null
    result.json.location.description == "Bronxville, Nowy Jork, Stany Zjednoczone"
    result.json.location.latitude == 40.93815439999999
    result.json.location.longitude == -73.8320784
    result.json.account.amount == 0
  }

  def "get information about user with no account profile by username from session"() {
    given:
    def credentials = [username: 'usernoaccount', password: '2aNJn29r']
    def response = post('/api/session', credentials)
    def token = response.json.token

    when:
    def result = get('/api/profile', new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "update user correctly"() {
    given:
    def credentials = [username: 'dennisritchie', password: 'TWkJb8ZB']
    def response = post('/api/session', credentials)
    def token = response.json.token
    def request = [
      firstName       : 'Denniss',
      lastName      : 'Ritchiee',
      email: 'denniss@ritchiee.com',
      username : 'dennisritchie',
      password      : 'TWkJb8ZB',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
      ]
    ]

    when:
    def result = put('/api/updateUser', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.OK
  }

  def "update user with wrong username"() {
    given:
    def credentials = [username: 'dennisritchie', password: 'TWkJb8ZB']
    def response = post('/api/session', credentials)
    def token = response.json.token
    def request = [
      firstName       : 'Denniss',
      lastName      : 'Ritchiee',
      email: 'denniss@ritchiee.com',
      username : 'dennisritchiee',
      password      : 'TWkJb8ZB',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
      ]
    ]

    when:
    def result = put('/api/updateUser', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "update user with existing email"() {
    given:
    def credentials = [username: 'dennisritchie', password: 'TWkJb8ZB']
    def response = post('/api/session', credentials)
    def token = response.json.token
    def request = [
      firstName       : 'Denniss',
      lastName      : 'Ritchiee',
      email: 'billgates@test.com',
      username : 'dennisritchie',
      password      : 'TWkJb8ZB',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
      ]
    ]

    when:
    def result = put('/api/updateUser', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

  def "update user that has non-existing location"() {
    given:
    def credentials = [username: 'usernolocation', password: '2aNJn29r']
    def response = post('/api/session', credentials)
    def token = response.json.token
    def request = [
      firstName       : 'Location',
      lastName      : 'None',
      email: 'location@test.com',
      username : 'usernolocation',
      password      : 'TWkJb8ZB',
      location   : [
        description: 'Seattle, Waszyngton, Stany Zjednoczone',
        latitude   : '47.6062095',
        longitude  : '-122.3320708'
      ]
    ]

    when:
    def result = put('/api/updateUser', request, new RequestParams(authToken: token))

    then:
    result.status == HttpStatus.BAD_REQUEST
  }

}
