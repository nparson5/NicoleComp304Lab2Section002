package nicole.parsons.nicolelab2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class NicoleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun enterBtnClicked(view: View?) {
        if (view == null) return
        startActivity(Intent(this, MenuActivity::class.java))
    }
}
