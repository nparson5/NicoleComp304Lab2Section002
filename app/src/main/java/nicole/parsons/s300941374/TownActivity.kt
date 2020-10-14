package nicole.parsons.s300941374
//nicole parsons - 300941374 - sec 002
import android.os.Bundle
import android.widget.CheckBox
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_town.*
import kotlinx.android.synthetic.main.activity_town.*

class TownActivity : AbstractOptionsMenuActivity() {

    private val townItems: Array<Home> = arrayOf(
        Home("989 Naah Street", 900000.00, HomeType.TOWN.name, R.drawable.town1, false),
        Home("980 Schrute Lane", 120000.00, HomeType.TOWN.name, R.drawable.town2, false),
        Home("908 Rad Road", 555000.00, HomeType.TOWN.name, R.drawable.town3, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town)
        nicoleImg.setImageResource(0)
        townItems.map { home ->



            val btn = CheckBox(this)
            btn.text = home.name

            HomeDatabase.getInstance(this)!!.homeDataDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { homes ->
                    for (f in homes) {

                        if (f == home) {
                            btn.isChecked = true

                        }
                    }
                }

            btn.setOnCheckedChangeListener { _, isChecked ->
                nicoleImg.setImageResource(home.img)
                if(!isChecked){
                    nicoleImg.setImageResource(0)
                }
                Single.fromCallable {
                    val dao = HomeDatabase.getInstance(this)!!.homeDataDao()
                    if (isChecked) {
                        dao.insert(home)

                    } else {
                        dao.delete(home)
                    }
                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
            nicoleTownLayout.addView(btn)
        }
    }
}
