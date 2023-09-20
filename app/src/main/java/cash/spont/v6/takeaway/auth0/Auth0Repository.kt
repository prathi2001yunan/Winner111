@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package cash.spont.v6.terminal.auth0

import cash.spont.v6.takeaway.auth0.Auth0RepositoryImpl
import cash.spont.v6.takeawayrepo.repository.AuthTokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface Auth0Repository {
    suspend fun getDeviceCode(): List<AuthCodeResponse>
    suspend fun getDeviceToken(deviceCode: String): List<AuthTokenResponse>
    suspend fun getNewToken(refreshToken: String): List<AuthTokenResponse>

    companion object {
        fun create(): Auth0Repository {
            return Auth0RepositoryImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}