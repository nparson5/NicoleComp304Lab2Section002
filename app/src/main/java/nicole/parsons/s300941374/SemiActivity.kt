package nicole.parsons.s300941374
//nicole parsons - 300941374 - sec 002
import android.os.Bundle
import android.widget.CheckBox
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_semi.*
import kotlinx.android.synthetic.main.activity_semi.*

class SemiActivity : AbstractOptionsMenuActivity() {

    private val semiItems: Array<Home> = arrayOf(
        Home("99878 Rat Road", 373888.00, HomeType.SEMI.name, R.drawable.semi1, false),
        Home("282 Bat Drive", 300000.00, HomeType.SEMI.name, R.drawable.semi2, false),
        Home("67 Yeet Street", 999999.00, HomeType.SEMI.name, R.drawable.semi3, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semi)
        nicoleImg.setImageResource(0)
        semiItems.map { home ->



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
            nicoleSemiLayout.addView(btn)
        }
    }
}
