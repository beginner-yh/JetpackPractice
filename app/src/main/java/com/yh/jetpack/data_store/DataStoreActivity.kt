package com.yh.jetpack.data_store

import android.content.Context
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.yh.jetpack.base.BaseActivity
import com.yh.jetpack.databinding.ActivityDataStoreBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * DataStore
 * https://developer.android.com/topic/libraries/architecture/datastore
 * 1.DataStore与sp
 * 2.DataStore与MMKV
 */
class DataStoreActivity : BaseActivity() {

    private lateinit var binding: ActivityDataStoreBinding
    val EXAMPLE_COUNTER = intPreferencesKey("example_counter")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //读取信息
        val exampleCounterFlow: Flow<Int> = dataStore.data.map { preferences ->
            preferences[EXAMPLE_COUNTER] ?: 0
        }
        lifecycleScope.launch {
            binding.tvCounter.text = exampleCounterFlow.first().toString()
            incrementCounter()
        }
    }

    /**
     * 写入信息
     */
    suspend fun incrementCounter() {
        dataStore.edit { settings ->
            val curCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = curCounterValue + 1
        }
    }
}