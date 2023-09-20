package cash.spont.v6.takeaway.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreHelper(private val context: Context) {

    // to make sure there is only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by
        preferencesDataStore("DataCheck")
        private const val KEY_ID_ACCESS = "KEY_ID_ACCESS"
        private const val KEY_ID_REFRESH_TOKEN = "KEY_ID_REFRESH_TOKEN"
        private const val KEY_ID_EXPIRES_IN = "KEY_ID_EXPIRES_IN"
        private const val KEY_ID_USER = "KEY_ID_USER"
        private const val KEY_ID_COMPANY = "KEY_ID_COMPANY"
        private const val KEY_ID_DEVICE_UUID = "KEY_ID_DEVICE_UUID"
    }

    private val keyAccessToken = stringPreferencesKey(KEY_ID_ACCESS)
    private val keyRefreshToken = stringPreferencesKey(KEY_ID_REFRESH_TOKEN)
    private val keyExpireIn = intPreferencesKey(KEY_ID_EXPIRES_IN)
    private val keyDeviceId = stringPreferencesKey(KEY_ID_DEVICE_UUID)

    val getAccessToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[keyAccessToken] ?: ""
        }

    suspend fun updateAccesToken(accessToken: String?) {
        context.dataStore.edit { preferences ->
            preferences[keyAccessToken] = accessToken ?: ""
        }
    }

    val getRefreshToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[keyRefreshToken] ?: ""
    }

    val getCurrentDeviceUuid: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[keyDeviceId] ?: ""
    }
    val getExpiryIn: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[keyExpireIn] ?: 0
    }

    suspend fun updateRefreshToken(refreshToken: String?) {
        context.dataStore.edit { preferences ->
            preferences[keyRefreshToken] = refreshToken ?: ""
        }
    }

    suspend fun updateExpiresIn(expiresIn: Int?) {
        context.dataStore.edit { preferences ->
            preferences[keyExpireIn] = expiresIn ?: 0
        }
    }

    suspend fun clearData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun updateUUID(uuid: String) {
        context.dataStore.edit { preferences ->
            preferences[keyDeviceId] = uuid ?: ""
        }
    }
}
