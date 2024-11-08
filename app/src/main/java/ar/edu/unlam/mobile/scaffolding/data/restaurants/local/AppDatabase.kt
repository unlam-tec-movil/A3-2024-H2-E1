package ar.edu.unlam.mobile.scaffolding.data.restaurants.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RestaurantEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}
