package nicole.parsons.s300941374
//nicole parsons - 300941374 - sec 002
import androidx.constraintlayout.solver.widgets.Flow
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface HomeDAO {

    @Query("SELECT * FROM homeData")
    fun getAll(): Flowable<List<Home>>

    @Query("SELECT * FROM homeData WHERE name = :name")
    fun getByName(name: String): Flowable<Home>

    @Query("SELECT * FROM homeData WHERE selected =1")
    fun getSelected(): Flowable<Home>


    @Update(onConflict = REPLACE)
    fun update(vararg home: Home)

    @Insert(onConflict = REPLACE)
    fun insert(home: Home)

    @Delete
    fun delete(home: Home)

    @Query("DELETE FROM homeData")
    fun deleteAllHome()
}