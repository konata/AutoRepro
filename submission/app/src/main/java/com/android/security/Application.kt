package com.android.security

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.internal.os.IResultReceiver
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
      context.startActivity(
        Intent(context, PocActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra(
          Consts.EXTRA_KEY,
          object : IResultReceiver.Stub() {
            override fun send(resultCode: Int, resultData: Bundle?) {
              cont.resume(resultCode)
            }
          }
        )
      )
      InstrumentationRegistry.getInstrumentation().uiAutomation.dropShellPermissionIdentity()
    }

    Log.d("natsuki", "value: $value")
    assertTrue("result value should be one", value == 100)
  }
}

class PocActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    intent.getParcelableExtra(Consts.EXTRA_KEY, IResultReceiver::class.java)!!.send(100, null)
    verticalLayout {
      button("send broadcast back") {
        onClick {
        }
      }
    }
  }
}