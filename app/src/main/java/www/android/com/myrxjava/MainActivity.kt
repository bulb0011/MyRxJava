package www.android.com.myrxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rxJava = TextRxJava()

        rxJava.chainedRx()

        findViewById<TextView>(R.id.retrofit)

    }



}
