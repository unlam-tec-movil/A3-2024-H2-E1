package ar.edu.unlam.mobile.scaffolding.data.di

import android.content.Context
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
import ar.edu.unlam.mobile.scaffolding.data.restaurants.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        Room
            .databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                name = "saborify_database",
            ).build()

    @Provides
    @Singleton
    fun provideUserOrderRepository(): UserOrderRepository = UserOrderRepository()
}
