package timebank.api

import groovy.json.JsonSlurper
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.test.context.support.WithMockUser
import spock.lang.Stepwise
import timebank.AbstractMvcSpec

import java.sql.DriverManager
import java.sql.PreparedStatement

@Stepwise
class UserControllerTest extends AbstractMvcSpec {

  @WithMockUser
  def "createUserTest"(){
    when:
    def credentials = ["firstName": "TestFirstName",
                       "lastName" : "TestLastName",
                       "email"    : "test@gmail.com",
                       "username" : "TestUsername",
                       "password" : "TestPassword0"]

    then:
    def resPost = post('/api/register', credentials)
    def resGet = get("/api/users")
    def jsonSlurper = new JsonSlurper()
    def bcrypt = new BCryptPasswordEncoder()
    def object = jsonSlurper.parseText(resGet.content)

    def ptr = object.username.indexOf('TestUsername')

    assert bcrypt.matches('TestPassword0', object.password.get(ptr))
    assert object.email.get(ptr) == 'test@gmail.com'
    assert object.firstName.get(ptr) == 'TestFirstName'
    assert object.lastName.get(ptr) == 'TestLastName'
  }

  def "deleteFromDatabase"(){
    when:
    Class.forName("com.mysql.jdbc.Driver")
    def connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timebank","root","root")
    def sqlQuery = new String("delete from user where username = 'TestUsername'")
    def preparedStm = connection.prepareStatement(sqlQuery)
    preparedStm.execute()

    then:
    connection.close()
  }

}
