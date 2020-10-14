package nicole.parsons.s300941374
//nicole parsons - 300941374 - sec 002
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.app.NavUtils

open class AbstractOptionsMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abstract_options_menu)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_apartmentBtn-> {
                startActivity(Intent(this, ApartmentActivity::class.java))
                true
            }
            R.id.menu_detachedBtn -> {
                startActivity(Intent(this, DetachedActivity::class.java))
                true
            }
            R.id.menu_condoBtn -> {
                startActivity(Intent(this, CondoActivity::class.java))
                true
            }
            R.id.menu_semiBtn -> {
                startActivity(Intent(this, SemiActivity::class.java))
                true
            }
            R.id.menu_townBtn -> {
                startActivity(Intent(this, TownActivity::class.java))
                true
            }
            R.id.home -> {
                super.onOptionsItemSelected(item)
                NavUtils.navigateUpFromSameTask(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}