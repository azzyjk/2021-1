package com.example.a201711425_jjw

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JJWMyReceiver : BroadcastReceiver() {
    val scope = CoroutineScope(Dispatchers.IO)

    override fun onReceive(context: Context, intent: Intent) {
        val pendingResult = goAsync() // finish할 때 까지는 MyReceiver 객체를 지우지 말라
        scope.launch{
            if(intent.action.equals("android.provider.Telephony.SMS_RECEIVED")){
                val bundle = intent.extras
                val objects = bundle?.get("pdus") as Array<Any>
                val sms = objects[0] as ByteArray
                val format = bundle.getString("format");
                val message = SmsMessage.createFromPdu(sms, format)
                val msg = message.messageBody
                if(msg.contains("City Hall")){
                    val tmpStr = msg.split("\n")
                    Log.i("TEST1", tmpStr.toString())
                    for(i in tmpStr){
                        Log.i("TEST2", i)
                        if(i.contains("City Hall")){
                            Log.i("TEST3", i)
                            val title = i.split(" ")
                            val newIntent = Intent(context, MainActivity::class.java)
                            newIntent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                            newIntent.putExtra("title", title[0])
                            Log.i("TEST4", title[0])
                            context.startActivity(newIntent)
                            break
                        }


                        }
                    }
                pendingResult.finish()


                }
            }
        }
    }
