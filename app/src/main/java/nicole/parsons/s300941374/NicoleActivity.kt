package nicole.parsons.s300941374
//nicole parsons - 300941374 - sec 002
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NicoleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nicole)
    }

    fun enterBtnClicked(view: View?) {
        if (view == null) return
        startActivity(Intent(this, MenuActivity::class.java))
    }
}