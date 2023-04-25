package co.yml.ychat.android.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import co.yml.ychat.Provider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

enum class ProviderKey {
    OPENAI,
    DUCAI
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ProviderRepository(private val providerMap : Map<ProviderKey, Provider>, private val context: Context) {

    private val PROVIDER_KEY = stringPreferencesKey("PROVIDER_KEY")


    val providerKeyFlow: Flow<ProviderKey> = context.dataStore.data
        .map { preferences ->
            val providerKeyName = preferences[PROVIDER_KEY] ?: ProviderKey.OPENAI.name
            ProviderKey.valueOf(providerKeyName)
        }

    suspend fun saveProvider(providerKey: ProviderKey) {
        context.dataStore.edit { preferences ->
            preferences[PROVIDER_KEY] = providerKey.name
        }
    }
}