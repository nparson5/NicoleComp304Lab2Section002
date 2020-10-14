package nicole.parsons.s300941374
//nicole parsons - 300941374 - sec 002
import android.os.Bundle
import android.widget.CheckBox

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_apartment.*

class ApartmentActivity : AbstractOptionsMenuActivity() {



//populate apartment type
    private val apartmentItems: Array<Home> = arrayOf(
        Home("454 Hulu Lane", 1000.00, HomeType.APARTMENT.name, R.drawable.apt1, false),
        Home("787 Flix Road", 1600.00, HomeType.APARTMENT.name, R.drawable.apt2, false),
        Home("77979 Crave Crescent", 950.00, HomeType.APARTMENT.name, R.drawable.apt3, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment)
        nicoleImg.setImageResource(0)//no image
        apartmentItems.map { home ->

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
            nicoleApartmentLayout.addView(btn)
        }
    }
}
