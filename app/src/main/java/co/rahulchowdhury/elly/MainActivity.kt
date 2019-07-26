package co.rahulchowdhury.elly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.rahulchowdhury.elly.ui.profile.ElephantProfileFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val elephantProfileFragment = ElephantProfileFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, elephantProfileFragment)
                .commit()
        }
    }
}
