package nicole.parsons.s300941374
//nicole parsons - 300941374 - sec 002
import android.media.Image
import android.os.Bundle
import android.widget.CheckBox
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_condo.*
import kotlinx.android.synthetic.main.activity_condo.*

class CondoActivity : AbstractOptionsMenuActivity() {

    private val condoItems: Array<Home> = arrayOf(
        Home("5656 Goog Street", 999000.00, HomeType.CONDO.name, R.drawable.con1, false),
        Home("49856 Explore Road", 409000.00, HomeType.CONDO.name, R.drawable.con2, false),
        Home("2434 Fire Lane", 1000000.00, HomeType.CONDO.name, R.drawable.con3, false)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condo)
        nicoleImg.setImageResource(0)
        condoItems.map { home ->



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
            nicoleCondoLayout.addView(btn)
        }
    }
}
