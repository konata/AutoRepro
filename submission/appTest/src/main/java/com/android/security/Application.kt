package com.android.security

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.runTest
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.verticalLayout
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


object Consts {
  const val BROADCAST_ACTION = "foobar"
  const val EXTRA_KEY = "key"
}

@RunWith(AndroidJUnit4::class)
class Tests {
  private val context = getApplicationContext<Context>()

  @Test
  fun `launch-and-wait-result`() = runTest {
    Log.e("natsuki", "hello")

    val value = suspendCoroutine { cont ->
      context.registerReceiver(object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
          cont.resume(intent.getIntExtra(Consts.EXTRA_KEY, 0))
        }
      }, IntentFilter(Consts.BROADCAST_ACTION), Context.RECEIVER_EXPORTED)
      context.startActivity(Intent(context, PocActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
      InstrumentationRegistry.getInstrumentation().uiAutomation.dropShellPermissionIdentity()
    }
    Log.e("natsuki", "world")
    assertTrue("result value should be one", value == 1 )
  }
}

class PocActivity: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState )
    sendBroadcast(Intent(Consts.BROADCAST_ACTION).putExtra(Consts.EXTRA_KEY, 100))
    verticalLayout {
      button("send broadcast back") {
        onClick {
        }
      }
    }
  }
}