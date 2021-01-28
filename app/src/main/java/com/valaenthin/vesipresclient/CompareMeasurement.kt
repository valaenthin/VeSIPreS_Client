package com.valaenthin.vesipresclient

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_compare.*
import kotlinx.android.synthetic.main.activity_take_measurement.*

class CompareMeasurement: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare)
        logCompare.movementMethod = ScrollingMovementMethod()
        btn_return2.setOnClickListener {
            finish()
        }

        if (measurements.size < 2)
        {
            finish()
            Toast.makeText(applicationContext, "There are two measurements needed for the Verification", Toast.LENGTH_SHORT).show()
            return
        }
        val probe1 = measurements.first()
        val probe2 = measurements.last()

        logAppend("Measurements selected")
        logAppend("Measurement 1:\n${probe1}\n")
        logAppend("Measurement 2:\n${probe2}\n")
        var result_ok=true

        if (probe1.digestG != probe2.digestG)
        {
            logAppend("Gateway has not same software stack as in previous measurement!")
            result_ok=false
        }
        if (probe1.digestE0 != probe2.digestE0)
        {
            logAppend("ECU 0 has not same software stack as in previous measurement!")
            result_ok=false
        }
        if (probe1.digestE1 != probe2.digestE1)
        {
            logAppend("ECU 1 has not same software stack as in previous measurement!")
            result_ok=false
        }
        if (probe1.digestE2 != probe2.digestE2)
        {
            logAppend("ECU 2 has not same software stack as in previous measurement!")
            result_ok=false
        }

        if (result_ok) {
            logAppend("The two software stacks are equal. Result: Pass!")
            resultText.text = "Result: Pass"
            statusCompare.setImageResource(R.drawable.ic_baseline_check_circle_24)
        } else {
            logAppend("The two software stacks are different. Result: Fail!")
            resultText.text = "Result: Fail"
            statusCompare.setImageResource(R.drawable.ic_baseline_warning_24)
        }

    }
    fun logAppend(s: String) {
        Log.d("ValDebugMsgComp", s)
        runOnUiThread {
            logCompare.append("$s\n")
        }
    }
}