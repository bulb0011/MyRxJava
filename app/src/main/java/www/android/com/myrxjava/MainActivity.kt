package www.android.com.myrxjava

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rxJava = TextRxJava()

        rxJava.chainedRx()

        findViewById<TextView>(R.id.retrofit).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                var intent=Intent(this@MainActivity,RxRetrofit::class.java)

                startActivity(intent)

            }
        })

//       var tr =TextRxJava()
//
//        tr.nestHppt()

    }



}
