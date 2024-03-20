package com.example.dessertrelease.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    /**
     * booleanPreferencesKey():
     * 불리언 타입의 키를 생성한다. <저장될 값과 동일한 타입의 키를 생성해야 한다.>
     * 이름은 언더바와 소문자로 이루어진 문자열이 권장된다.
     * 해당 키를 활용하여 DataStore의 값을 읽고 저장할 수 있다.
     */
    private companion object {
        val IS_LINEAR_LAYOUT = booleanPreferencesKey("is_linear_layout")
        const val TAG = "UserPreferencesRepo" //예외를 위한 로그 메세지.
    }

    /**
     * DataStore<Preferences>.edit():
     * DataStore의 값을 변경하는 메서드.
     * 해당 람다에 선언된 작업들은 순차적으로 실행되는데 하나라도 실패하면 이전에 실행된 모든 게 롤백되어 변경사항이 적용되지 않는 단일 트랜잭션으로 실행된다.
     * 해당 함수가 호출되어 값이 저장되면 앱의 캐시나 데이터를 따로 삭제하지 않는 한 영구적으로 보존된다.
     */
    suspend fun saveLayoutPreference(isLinearLayout: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LINEAR_LAYOUT] = isLinearLayout //키를 사용하여 파라미터로 받은 값을 DataStore에 저장한다.
        }
    }

    /**
     * DataStore<Preferences>.data:
     * DataStore의 값을 읽는 프로퍼타로, 파일로부터 데이터를 읽어오기 때문에 IOException 예외가 발생할 수 있다.
     * Flow<Preferences>를 반환하므로 데이터의 수집/방출이 가능하다.
     */
    val isLinearLayout: Flow<Boolean> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences()) //비어 있는 Preferences 반환.
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[IS_LINEAR_LAYOUT] ?: true //매칭되는 키가 없으면 널을 반환하므로 기본 값을 설정하는 것이 좋다.
        } // Flow<Preferences> -> Flow<Boolean> 변환.
}