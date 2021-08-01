package com.sunbird.test.datastore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.sunbird.test.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.xutils.common.util.LogUtil

class DataStoreActivity : AppCompatActivity() {

  companion object {
    const val PREFERENCE_NAME = "settings"
    const val USER_STORE_NAME = "user"
    val KEY_EXAMPLE_COUNTER = intPreferencesKey("example_counter")
  }

  private val dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

  val exampleCounterFlow: Flow<Int> by lazy {
    dataStore.data.map {
      it[KEY_EXAMPLE_COUNTER] ?: 0
    }
  }

  private val userDataStore: DataStore<UserPreferences> by dataStore(
    fileName = "user_prefs.pb",
    serializer = UserPreferencesSerializer
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_data_store)

    lifecycleScope.launch {
      exampleCounterFlow.collect {
        LogUtil.i("example_counter:${it}")
      }
    }

    lifecycleScope.launch {
      userDataStore.data.collect {
        LogUtil.i("sortOrder:${it.sortOrder}")
      }
    }


    lifecycleScope.launch {
      delay(1000)
      incrementCounter()
      delay(1000)
      incrementCounter()
      delay(1000)
      incrementCounter()
      delay(1000)
      incrementCounter()
      delay(1000)
      incrementCounter()
      delay(1000)
      incrementCounter()
      delay(1000)
      incrementCounter()
      delay(1000)
      incrementCounter()
      delay(1000)
      incrementCounter()
      delay(1000)
      incrementCounter()
      delay(1000)
      incrementCounter()
      delay(1000)
      enableSortByDeadline(true)
      delay(1000)
      enableSortByDeadline(false)
      delay(1000)
    }
  }

  suspend fun incrementCounter() {
    dataStore.edit { settings ->
      val currentCounterValue = settings[KEY_EXAMPLE_COUNTER] ?: 0
      settings[KEY_EXAMPLE_COUNTER] = currentCounterValue + 1
    }
  }

  suspend fun enableSortByDeadline(enable: Boolean) {
    userDataStore.updateData { preferences ->
      val currentOrder = preferences.sortOrder
      val newSortOrder = if (enable) {
        if (currentOrder == UserPreferences.SortOrder.BY_PRIORITY) {
          UserPreferences.SortOrder.BY_DEADLINE_AND_PRIORITY
        } else {
          UserPreferences.SortOrder.BY_DEADLINE
        }
      } else {
        if (currentOrder == UserPreferences.SortOrder.BY_DEADLINE_AND_PRIORITY) {
          UserPreferences.SortOrder.BY_PRIORITY
        } else {
          UserPreferences.SortOrder.NONE
        }
      }
      preferences.toBuilder().setSortOrder(newSortOrder).build()
    }
  }
}