package com.example.week14_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.SmsMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyReceiver : BroadcastReceiver() {
    val usagePattern = Regex("""^[가-힣a-zA-Z0-9]+$""")
    val paymentPattern = Regex("""^*\d{3}원$""")
    val dayPattern = Regex("""^\d{2}/\d{2}""")
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
                if(msg.contains("건국카드")){
                    val tmpStr = msg.split("\n")
                    var resultStr = ""
                    for(str in tmpStr.subList(1, tmpStr.size)){
                        if(usagePattern.containsMatchIn(str)){
                            resultStr += "$str : "
                        }
                        if(paymentPattern.containsMatchIn(str)){
                            resultStr += "$str : "
                        }
                        if(dayPattern.containsMatchIn(str)){
                            val tmp = str.split(" ")
                            val daytmp = tmp[0].split("/")
                            val month = daytmp[0]
                            val day = daytmp[1]


                            resultStr += "${month}월 ${day}일 카드 승인됨"
                        }
                    }
                    if(!resultStr.isEmpty()){
                        val newIntent = Intent(context, MainActivity::class.java)
                        newIntent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        newIntent.putExtra("msg", resultStr)
                        context.startActivity(newIntent)
                    }
                    pendingResult.finish()
                }
            }
        }

    }
}
