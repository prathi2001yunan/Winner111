package cash.spont.v6.takeaway.auth0

import cash.spont.v6.takeawayrepo.repository.AuthTokenResponse
import cash.spont.v6.terminal.auth0.Auth0Repository
import cash.spont.v6.terminal.auth0.AuthCodeResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.Parameters

class Auth0RepositoryImpl(val client: HttpClient) : Auth0Repository {
    override suspend fun getDeviceCode(): List<AuthCodeResponse> {
        return try {
            val response = client.post<AuthCodeResponse> {
                url("https://spont-staging.eu.auth0.com/oauth/device/code")
                body = FormDataContent(
                    Parameters.build {
                        append("client_id", "PhNt5IztNApIoGfhlNNfMGbcwm3QIIVe")
                        append("audience", "https://auth0-jwt-authorizer")
                        append("scope", "profile email offline_access")
                    }
                )
            }
            val responseData: AuthCodeResponse = response
            print(responseData)
            client.close()
            return listOf(responseData)
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getDeviceToken(deviceCode: String): List<AuthTokenResponse> {
        return try {
            val response = client.post<AuthTokenResponse> {
                url("https://spont-staging.eu.auth0.com/oauth/token")
                body = FormDataContent(
                    Parameters.build {
                        append("client_id", "PhNt5IztNApIoGfhlNNfMGbcwm3QIIVe")
                        append(
                            "grant_type", "urn:ietf:params:oauth:grant-type:device_code"
                        )
                        append("device_code", deviceCode)
                    }
                )
            }
            val responseToken: AuthTokenResponse = response
            print(responseToken)
            client.close()
            return listOf(responseToken)
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getNewToken(refreshToken: String): List<AuthTokenResponse> {
        return try {
            val response = client.post<AuthTokenResponse> {
                url("https://spont-staging.eu.auth0.com/oauth/token")
                body = FormDataContent(
                    Parameters.build {
                        append("client_id", "PhNt5IztNApIoGfhlNNfMGbcwm3QIIVe")
                        append("grant_type", "refresh_token")
                        append(
                            "client_secret",
                            "sAwr-01ZUp3uawuj-c-KKRD0SUPmQWfswDtHchmGQ146xFAHOcZxd90_8j7hNOWj"
                        )
                        append("refresh_token", refreshToken)
                    }
                )
            }
            val responseToken: AuthTokenResponse = response
            print(responseToken)
            client.close()
            return listOf(responseToken)
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }
}
