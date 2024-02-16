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
import ru.adaptum.adaptumandroid.domain.handler.TokenDataHandler
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitDefault(tokenDataHandler: TokenDataHandler): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideHttpClient(tokenDataHandler))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    @Provides
    @Singleton
    @Named("AuthInterceptor")
    fun provideRetrofit(tokenDataHandler: TokenDataHandler): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideHttpClient(tokenDataHandler))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    @Provides
    @Singleton
    @Named("NoAuthInterceptor")
    fun provideRetrofitNoAuth(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
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

//    @Provides
//    @Named("AuthInterceptor")
//    fun provideAuthApi(
//        @Named("AuthInterceptor") retrofit: Retrofit,
//    ): AuthApi = retrofit.create(AuthApi::class.java)
//
//    @Provides
//    @Named("NoAuthInterceptor")
//    fun provideNoAuthApi(
//        @Named("NoAuthInterceptor") retrofit: Retrofit,
//    ): AuthApi = retrofit.create(AuthApi::class.java)
//
//    @Provides
//    fun provideAdaptListApi(retrofit: Retrofit): AdaptListApi = retrofit.create(AdaptListApi::class.java)
//
//    @Provides
//    fun provideProfileDataApi(retrofit: Retrofit): ProfileDataApi = retrofit.create(ProfileDataApi::class.java)
//
//    @Provides
//    fun provideEventsApi(retrofit: Retrofit): EventsApi = retrofit.create(EventsApi::class.java)
//
//    @Provides
//    fun provideMessagesApi(retrofit: Retrofit): MessagesApi = retrofit.create(MessagesApi::class.java)

    companion object {
        private const val BASE_URL = "http://79.174.94.65:8000/"
    }
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
