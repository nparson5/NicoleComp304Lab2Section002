package nicole.parsons.s300941374
//nicole parsons - 300941374 - sec 002
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "homeData")
data class Home(
    @PrimaryKey() var name: String,
    var price: Double,
    var type: String,
    var img: Int,//image resource
    var selected: Boolean
)
