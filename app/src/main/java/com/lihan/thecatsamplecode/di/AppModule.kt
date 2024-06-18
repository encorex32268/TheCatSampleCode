package com.lihan.thecatsamplecode.di

import com.lihan.thecatsamplecode.BuildConfig
import com.lihan.thecatsamplecode.data.remote.CatAPI
import com.lihan.thecatsamplecode.data.repository.MainRepositoryImpl
import com.lihan.thecatsamplecode.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesCatAPI(
        retrofit: Retrofit
    ): CatAPI {
        return retrofit.create(CatAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesMainRepository(
        api: CatAPI
    ): MainRepository {
        return MainRepositoryImpl(api)
    }
}