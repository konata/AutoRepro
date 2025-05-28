package com.android.security

import com.android.sts.common.SystemUtil
import com.android.sts.common.tradefed.testtype.NonRootSecurityTestCase
import com.android.tradefed.testtype.DeviceJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(DeviceJUnit4ClassRunner::class)
class Runner : NonRootSecurityTestCase() {
  object App {
    @Suppress("ConstPropertyName")
    private const val ProjectIdentifier = "app"

    @Suppress("ConstPropertyName")
    const val Instrumented = "com.android.security.Tests"

    @Suppress("ConstPropertyName")
    const val Method = "launch-and-wait-result"

    @Suppress("ConstPropertyName")
    const val PackageName = "com.android.security.${ProjectIdentifier}_AutoReproPlaceholder"

    @Suppress("ConstPropertyName")
    const val Apk = "${ProjectIdentifier}_AutoReproPlaceholder.apk"
  }

  @Test
  fun `test-run-application-instrumentation`() {
    val exemption = SystemUtil.withSetting(device, SystemUtil.Namespace.GLOBAL, "hidden_api_policy", "1")
    uninstallPackage(device, App.PackageName)
    installPackage(App.Apk)
    runDeviceTests(App.PackageName, App.Instrumented, App.Method)
    exemption.close()
  }
}
