package io.realworld.api

import io.realworld.api.models.entities.SignupData
import io.realworld.api.models.requests.SignupRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import kotlin.random.Random

class ConduitClientTests {

    private val conduitClient = ConduitClient()

    @Test
    fun `GET articles`() {
        runBlocking {
            val articles = conduitClient.publicApi.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by author`() {
        runBlocking {
            val articles = conduitClient.publicApi.getArticles(author = "Gerome")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `GET articles by tag`() {
        runBlocking {
            val articles = conduitClient.publicApi.getArticles(tag = "welcome")
            assertNotNull(articles.body()?.articles)
        }
    }

    @Test
    fun `POST users - create user`() {
        val signupData = SignupData(
            "testmail${Random.nextInt(999, 9999)}@test.com",
            "${Random.nextInt(99999, 9999999)}",
            "rand_user_${Random.nextInt(99, 999)}"
        )
        runBlocking {
            val resp = conduitClient.publicApi.signupUser(SignupRequest(signupData))
            assertEquals(signupData.username, resp.body()?.user?.username)
        }
    }
}