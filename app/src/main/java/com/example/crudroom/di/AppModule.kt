package com.example.crudroom.di

import android.app.Application
import androidx.room.Room
import com.example.crudroom.feature_users.data.local_source.local.UserDatabase
import com.example.crudroom.feature_users.data.repository.UserRepositoryImpl
import com.example.crudroom.feature_users.domain.repository.UserRepository
import com.example.crudroom.utils.DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserDatabase(app: Application) = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        DATABASE
    ).build()


    @Provides
    @Singleton
    fun provideRepository(db:UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao)
    }


}