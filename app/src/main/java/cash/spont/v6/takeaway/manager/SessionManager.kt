package cash.spont.v6.takeaway.manager

import android.content.Context

class SessionManager(context: Context) {
    val dataStore = DataStoreHelper(context)

    suspend fun setAuth(accessToken: String, refreshToken: String, expiryInt: Int) {
        dataStore.updateAccesToken(accessToken)
        dataStore.updateRefreshToken(refreshToken)
        dataStore.updateExpiresIn(expiryInt)
    }

    suspend fun getAccessToken(): String? {
        return dataStore.getAccessToken.toString() ?: ""
    }

    suspend fun getRenewToken(): String? {
        return dataStore.getRefreshToken.toString() ?: ""
    }

    suspend fun getCurrentDeviceUuid(): String? {
        return dataStore.getCurrentDeviceUuid.toString() ?: ""
    }

    suspend fun setDevice(device: String) {
        return dataStore.updateUUID(device)
    }

    suspend fun logout() {
        return dataStore.clearData()
    }
}
