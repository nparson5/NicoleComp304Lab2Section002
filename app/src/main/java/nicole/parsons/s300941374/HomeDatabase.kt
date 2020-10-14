package nicole.parsons.s300941374


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//nicole parsons - 300941374 - sec 002
@Database(entities = [Home::class], version = 1)
abstract class HomeDatabase: RoomDatabase() {
    abstract fun homeDataDao(): HomeDAO

    companion object {
        private var instance: HomeDatabase? = null

        fun getInstance(context: Context): HomeDatabase? {
            if (instance == null) {
                synchronized(HomeDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        HomeDatabase::class.java, "home.db")
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}
