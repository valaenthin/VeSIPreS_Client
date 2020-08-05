package com.valaenthin.vesipresclient

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_compare.*
import kotlinx.android.synthetic.main.activity_take_measurement.*

class CompareMeasurement: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare)
        btn_return2.setOnClickListener {
            finish()
        }

        if (measurements.size < 2)
        {
            finish()
            Toast.makeText(applicationContext, "There are two measurements needed for the Verification", Toast.LENGTH_SHORT).show()
        }
        val probe1 = measurements.first()
        val probe2 = measurements.last()

        logAppend("Measurements selected")


        statusCompare.setImageResource(R.drawable.ic_baseline_warning_24)
        statusCompare.setImageResource(R.drawable.ic_baseline_check_circle_24)






    }
    fun logAppend(s: String) {
        Log.d("ValiComp", s)
        runOnUiThread {
            logCompare.append("$s\n")
        }
    }
}