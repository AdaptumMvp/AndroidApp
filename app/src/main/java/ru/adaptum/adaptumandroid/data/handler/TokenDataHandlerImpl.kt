package ru.adaptum.adaptumandroid.data.handler

import android.content.Context
import ru.adaptum.adaptumandroid.domain.entity.Token
import ru.adaptum.adaptumandroid.domain.handler.TokenDataHandler
import javax.inject.Inject

class TokenDataHandlerImpl
    @Inject
    constructor(private val context: Context) : TokenDataHandler {
        override fun saveToken(token: Token) {
            getPrefs().edit()
                .putString(TOKEN_DATA_KEY, token.token).apply()
        }

        override fun getToken() =
            Token(
                getPrefs()
                    .getString(TOKEN_DATA_KEY, "") ?: "",
            )

        override fun removeToken() {
            getPrefs().edit().remove(TOKEN_DATA_KEY).apply()
        }

        override fun isTokenEmpty() =
            getPrefs().getString(TOKEN_DATA_KEY, "")
                .isNullOrBlank()

        override fun isTokenNotEmpty() = !getPrefs().getString(TOKEN_DATA_KEY, "").isNullOrBlank()

        private fun getPrefs() = context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE)

        companion object {
            private const val SHARED_PREFS_FILE_NAME = "adaptum_app_prefs"
            private const val TOKEN_DATA_KEY = "token_data"
        }
    }
