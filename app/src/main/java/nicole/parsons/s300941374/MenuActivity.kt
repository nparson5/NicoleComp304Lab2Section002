package nicole.parsons.s300941374
//nicole parsons - 300941374 - sec 002
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AbstractOptionsMenuActivity(), ClearCartFragment.NoticeDialogListener,
    RadioGroup.OnCheckedChangeListener {


    private var passingHomeName: String? = null
    private var passingHomePrice: Double? = null
    private var disposable: CompositeDisposable = CompositeDisposable()
    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val rg = RadioGroup(this)
        rg.orientation = RadioGroup.VERTICAL
        rg.setOnCheckedChangeListener(this)
        val disp = HomeDatabase.getInstance(this)!!.homeDataDao().getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { homes ->
                refresh(rg)

                for (home in homes) {
                    fillLayout(home,rg)

                }
                nicoleCheckoutItemNamesLayout.addView(rg)

            }
        this.disposable.add(disp)



    }
    override fun onCheckedChanged(rg: RadioGroup, checkedId: Int)  {

        val radio: RadioButton = findViewById(checkedId)
        val homeName: String = radio.text.toString()
       // val dao = HomeDatabase.getInstance(this)!!.homeDataDao()

/*
        HomeDatabase.getInstance(this)!!.homeDataDao().getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { homes ->
                for (home in homes) {
                    home.selected=false
                    dao.update(home)

                }
            }

*/
         HomeDatabase.getInstance(this)!!.homeDataDao().getByName(homeName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { home ->
                  //  home.selected=true;
                    passingHomeName = home.name
                    passingHomePrice = home.price
                    nicoleTotalCostTxt.setText("\$%.2f".format(passingHomePrice))

            }


    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        val disp = Single.fromCallable {
            HomeDatabase.getInstance(this)!!.homeDataDao().deleteAllHome()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        this.disposable.add(disp)
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
    }


    fun openClearCartDialog(view: View) {
        when (view.id) {
            R.id.nicoleClearCartBtn -> {
                val fragment = ClearCartFragment()
                fragment.show(supportFragmentManager, "Clear Cart")
            }
        }
    }

    fun goToPaymentScreen(view: View) {
        when (view.id) {
            R.id.nicoleProceedToPaymentBtn -> {
                if(nicoleTotalCostTxt.text.trim().length<=6){
                    nicoleTotalCostTxt.error = "This field is required!"
                    return
                }
                else {
                    val intent = Intent(this,PaymentActivity::class.java)
                    intent.putExtra("home", passingHomeName)
                    intent.putExtra("homePrice",passingHomePrice)
                    startActivity(intent)
                }
            }
        }
    }


    private fun refresh(rg :RadioGroup) {
        nicoleCheckoutItemNamesLayout.removeAllViews()
        rg.removeAllViews()
        nicoleCheckoutItemPricesLayout.removeAllViews()
    }

    private fun fillLayout(home: Home, rg: RadioGroup) {
        val id : Int =View.generateViewId()


        val nameTv = RadioButton(this)
        nameTv.text = home.name
        nameTv.id=id

        rg.addView(nameTv)


        val costTv = TextView(this)
        costTv.text = ("\$%.2f".format(home.price))
        costTv.textSize = (23.5F)
        nicoleCheckoutItemPricesLayout.addView(costTv)
    }
}