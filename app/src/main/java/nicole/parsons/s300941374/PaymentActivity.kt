package nicole.parsons.s300941374
//nicole parsons - 300941374 - sec 002
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {


    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        nicoleCardInfo.visibility = View.GONE;

        val home=intent.getStringExtra("home")

        val homePrice=intent.getDoubleExtra("homePrice",0.0)

                val info = TextView(this)
                info.text = "Purchasing ${home} for \$%.2f".format(homePrice)
                nicolePurchaseInfoTv.addView(info)


/*
        val info = TextView(this)
        info.text = "YEEEETERONI"
        nicolePurchaseInfoTv.addView(info)
*/
    }



    fun radio_button_click(view: View) {
        if(nicoleCashRadioButton.isChecked)
        {

            nicoleCardInfo.visibility = View.GONE;
            nicoleCardTxt.error = null
            nicoleCvcTxt.error = null
            nicoleYearTxt.error = null
            nicoleMonthTxtEntry.error = null
        }
        else{

            nicoleCardInfo.visibility = View.VISIBLE;
        }

    }

    fun submitOrder(view: View) {
        val name: String = nicoleFullNameTxt.text.toString()
        val email: String = nicoleEmailTxt.text.toString()
        val address: String = nicoleAddressTxt.text.toString()
        val card: String = nicoleCardTxt.text.toString()
        val cvc: String = nicoleCvcTxt.text.toString()
        val year: String = nicoleYearTxt.text.toString()
        val nicoleMonthTxt: String = nicoleMonthTxtEntry.text.toString()

        //check if the EditText have values or not
        if(name.trim().isEmpty()) {

            nicoleFullNameTxt.error = "This field is required!";
        }else

            if(!email.contains("@")){
                nicoleEmailTxt.error = "Enter a proper email!";
            }else

                if(address.trim().isEmpty()){
                    nicoleAddressTxt.error = "This field is required!"
                }else
                    if(nicoleCardInfo.visibility ==View.VISIBLE){
                        if(card.trim().isEmpty()){
                            nicoleCardTxt.error = "This field is required!"
                        }else
                            if(cvc.trim().isEmpty()){
                                nicoleCvcTxt.error = "This field is required!"
                            }else
                                if(year.trim().isEmpty()){
                                    nicoleYearTxt.error = "This field is required!"
                                }else
                                    if(nicoleMonthTxt.trim().isEmpty()){
                                        nicoleMonthTxtEntry.error = "This field is required!"
                                    }
                    }else

                    {

                        //Make Toast Message upon clicking Menu item
                        Toast.makeText(
                            this@PaymentActivity,
                            "Order successful!",
                            Toast.LENGTH_LONG
                        ).show()

                        startActivity(Intent(this, NicoleActivity::class.java))
                    }


    }


}