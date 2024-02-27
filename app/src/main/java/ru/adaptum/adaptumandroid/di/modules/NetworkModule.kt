package ru.adaptum.adaptumandroid.di.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.adaptum.adaptumandroid.BuildConfig
import ru.adaptum.adaptumandroid.data.network.api.AdaptListApi
import ru.adaptum.adaptumandroid.data.network.api.AuthApi
import ru.adaptum.adaptumandroid.data.network.api.EventsApi
import ru.adaptum.adaptumandroid.data.network.api.MessagesApi
import ru.adaptum.adaptumandroid.data.network.api.ProfileDataApi
import ru.adaptum.adaptumandroid.domain.handler.TokenDataHandler
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention
annotation class AuthInterceptorApi

@Qualifier
@Retention
annotation class NoAuthInterceptorApi

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitDefault(tokenDataHandler: TokenDataHandler): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideHttpClient(tokenDataHandler))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    @Provides
    @Singleton
    @AuthInterceptorApi
    fun provideRetrofit(tokenDataHandler: TokenDataHandler): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideHttpClient(tokenDataHandler))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    @Provides
    @Singleton
    @NoAuthInterceptorApi
    fun provideRetrofitNoAuth(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideHttpClientNoAuth())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    private fun provideHttpClient(tokenDataHandler: TokenDataHandler) =
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(AuthInterceptor(tokenDataHandler))
            .build()

    private fun provideHttpClientNoAuth() =
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @AuthInterceptorApi
    fun provideAuthApi(
        @AuthInterceptorApi retrofit: Retrofit,
    ): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @NoAuthInterceptorApi
    fun provideNoAuthApi(
        @NoAuthInterceptorApi retrofit: Retrofit,
    ): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    fun provideAdaptListApi(retrofit: Retrofit): AdaptListApi = retrofit.create(AdaptListApi::class.java)

    @Provides
    fun provideProfileDataApi(retrofit: Retrofit): ProfileDataApi = retrofit.create(ProfileDataApi::class.java)

    @Provides
    fun provideEventsApi(retrofit: Retrofit): EventsApi = retrofit.create(EventsApi::class.java)

    @Provides
    fun provideMessagesApi(retrofit: Retrofit): MessagesApi = retrofit.create(MessagesApi::class.java)
}

private class AuthInterceptor(
    private val tokenDataHandler: TokenDataHandler,
) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder =
            originalRequest.newBuilder()
                .header("Authorization", "Token ${tokenDataHandler.getToken().token}")
                .method(originalRequest.method, originalRequest.body)

        val modifiedRequest = requestBuilder.build()
        return chain.proceed(modifiedRequest)
    }
}
