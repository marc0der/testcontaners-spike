package testcontainer.spike

import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

object DatabaseFixture {

    const val DB_NAME = "products"
    const val DB_USERNAME = "developer"
    const val DB_PASSWORD = "password123"

    private val postgresContainer =
        PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:11"))
            .apply {
                withDatabaseName(DB_NAME)
                withUsername(DB_USERNAME)
                withPassword(DB_PASSWORD)
                withExposedPorts(5432)
            }

    fun withDatabase(testFunc: (String, Int) -> Unit) {
        postgresContainer.start()
        val dbHost = postgresContainer.host
        val dbPort = postgresContainer.firstMappedPort
        try {
            testFunc(dbHost, dbPort)
        } finally {
            postgresContainer.stop()
        }
    }
}
