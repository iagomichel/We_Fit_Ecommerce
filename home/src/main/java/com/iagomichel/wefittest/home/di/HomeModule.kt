package com.iagomichel.wefittest.home.di

import com.iagomichel.wefittest.home.BuildConfig
import com.iagomichel.wefittest.home.data.remote.HomeService
import com.iagomichel.wefittest.home.data.repository.MovieRepositoryImpl
import com.iagomichel.wefittest.home.domain.repository.MovieRepository
import com.iagomichel.wefittest.home.domain.usecase.UseCaseFetchMovies
import com.iagomichel.wefittest.home.domain.usecase.UseCaseFetchMoviesImpl
import com.iagomichel.wefittest.network.RetrofitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideHomeRetrofit(): HomeService {
        return RetrofitConfig.create(
            isDebugMode = BuildConfig.DEBUG,
            baseUrl = BuildConfig.API_DEFAULT_URL,
            service = HomeService::class.java
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        homeService: HomeService
    ): MovieRepository {
        return MovieRepositoryImpl(homeService)
    }

    @Provides
    @Singleton
    fun provideUseCaseFetchMovies(
        movieRepository: MovieRepository
    ): UseCaseFetchMovies {
        return UseCaseFetchMoviesImpl(movieRepository)
    }
}
