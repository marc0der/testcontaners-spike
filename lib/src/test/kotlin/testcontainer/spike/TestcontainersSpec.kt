package testcontainer.spike

import io.kotest.assertions.fail
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import testcontainer.spike.DatabaseFixture.withDatabase
import java.sql.DriverManager
import java.util.*


class TestcontainersSpec : WordSpec({
    "testcontainers" should {
        "be accessible in Jenkins" {
            withDatabase { dbHost, dbPort ->
                val url = "jdbc:postgresql://$dbHost:$dbPort/products"
                val props = with(Properties()){
                    setProperty("user", "developer")
                    setProperty("password", "password123")
                    this
                }
                val conn = DriverManager.getConnection(url, props)
                val prepareStatement = conn.prepareStatement("SELECT 1")
                val resultSet = prepareStatement.executeQuery()
                if(resultSet.next()){
                    val result = resultSet.getInt(1)
                    result shouldBe 1
                } else {
                    fail("no result found")
                }
            }
        }
    }
})
