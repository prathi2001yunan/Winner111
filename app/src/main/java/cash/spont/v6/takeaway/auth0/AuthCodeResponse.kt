package cash.spont.v6.terminal.auth0

import kotlinx.serialization.Serializable

@Serializable
data class AuthCodeResponse(
    val device_code: String? = null,
    val user_code: String? = null,
    val verification_uri: String? = null,
    val verification_uri_complete: String? = null,
    val expires_in: Int = 0,
    val interval: Int = 0
)
