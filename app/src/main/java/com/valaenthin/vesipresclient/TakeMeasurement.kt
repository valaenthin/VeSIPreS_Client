package com.valaenthin.vesipresclient

import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_take_measurement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.Socket
import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.*
import android.text.method.ScrollingMovementMethod as ScrollingMovementMethod1

class TakeMeasurement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_measurement)
        logMeasure.movementMethod = ScrollingMovementMethod1()

        btn_return.setOnClickListener {
            finish()
        }

        lifecycleScope.launch(Dispatchers.Default) {
            val ip_address = "192.168.0.101"
            val ip_port = 7777
            logAppend("connect to address $ip_address on port $ip_port...")
            val connection = Socket(ip_address, ip_port)
            val reader = Scanner(connection.getInputStream())
            val writer = connection.getOutputStream()
            logAppend("socket opened")


            //logTextView.append("send request...\n")
            val jSend = JSONObject()
            val secureRandom = SecureRandom()
            val nonce = secureRandom.nextLong()
            logAppend("Nonce: ${nonce.toString()}")  // TO DELETE: this line causes crashes
            jSend.put("Request", 1)
            jSend.put("Nonce", nonce)
            logAppend("JSON: ${jSend.toString()}\n")
            writer.write(jSend.toString().toByteArray())


            logAppend("wait for measurement...")
            val data_recv = reader.nextLine()
//            while (reader.hasNextLine()) {
//                data_recv.plus(reader.nextLine())
//                break
//            }
            logAppend("received answer: $data_recv")
            val jReceive = JSONObject(data_recv)
            logAppend("received answer JSON: ${jReceive.toString()}")

            logAppend("store measurement...")
            measurements.add(Measurement("Measurement ${measurements.size}", LocalDateTime.now(),1,2,3,4,5))
            reader.close()
            connection.close()

            logAppend(measurements.toString())


            runOnUiThread {
                status_finish.visibility = View.VISIBLE
                status_working.visibility = View.INVISIBLE
//                findViewById<TextView>(R.id.emptyHint).visibility = View.VISIBLE
                recyclerAdapter.notifyDataSetChanged()
            }

            logAppend("Done!")


        }

    }

    fun logAppend(s: String) {
        d("ValiTake",s)
        runOnUiThread {
            logMeasure.append("$s\n")
        }
    }

}
