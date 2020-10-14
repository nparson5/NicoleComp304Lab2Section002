package nicole.parsons.s300941374;
//nicole parsons - 300941374 - sec 002
import android.os.Bundle
import android.widget.CheckBox
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detached.*
import kotlinx.android.synthetic.main.activity_detached.*

class DetachedActivity : AbstractOptionsMenuActivity() {

    private val detachedItems: Array<Home> = arrayOf(
        Home("676 Amazon Drive", 800000.00, HomeType.DETACHED.name, R.drawable.det1, false),
        Home("303 Fed Lane", 430000.00, HomeType.DETACHED.name, R.drawable.det2, false),
        Home("868 Ups Street", 450000.00, HomeType.DETACHED.name, R.drawable.det3, false)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detached)
        nicoleImg.setImageResource(0)
        detachedItems.map { home ->



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
            nicoleDetachedLayout.addView(btn)
        }
    }
}
