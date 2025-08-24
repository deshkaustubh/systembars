package tech.kaustubhdeshpande.systembars

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat

/**
 * A simple composable to set system bar colors and icon contrast in a Compose app.
 *
 * @param statusBarColor REQUIRED. The color for the status bar.
 * @param navigationBarColor REQUIRED. The color for the navigation bar.
 * @param lightStatusBarIcons Optional. Defaults to true if [statusBarColor] is light.
 * @param lightNavigationBarIcons Optional. Defaults to true if [navigationBarColor] is light.
 * @param content Your screen content.
 */
@Composable
fun SystemBars(
    statusBarColor: Color,
    navigationBarColor: Color,
    lightStatusBarIcons: Boolean = statusBarColor.luminance() > 0.5,
    lightNavigationBarIcons: Boolean = navigationBarColor.luminance() > 0.5,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val window = (context as? Activity)?.window
    val isAndroid14OrAbove = Build.VERSION.SDK_INT >= 34

    LaunchedEffect(statusBarColor, navigationBarColor, lightStatusBarIcons, lightNavigationBarIcons, isAndroid14OrAbove) {
        window?.let {
            if (!isAndroid14OrAbove) {
                // Android 13 and below: set system bar colors via window
                @Suppress("DEPRECATION")
                it.statusBarColor = statusBarColor.toArgb()
                @Suppress("DEPRECATION")
                it.navigationBarColor = navigationBarColor.toArgb()
                WindowCompat.getInsetsController(it, it.decorView).apply {
                    isAppearanceLightStatusBars = lightStatusBarIcons
                    isAppearanceLightNavigationBars = lightNavigationBarIcons
                }
            } else {
                // Android 14+: set transparent, draw behind in Compose
                @Suppress("DEPRECATION")
                it.statusBarColor = android.graphics.Color.TRANSPARENT
                @Suppress("DEPRECATION")
                it.navigationBarColor = android.graphics.Color.TRANSPARENT
                WindowCompat.getInsetsController(it, it.decorView).apply {
                    isAppearanceLightStatusBars = lightStatusBarIcons
                    isAppearanceLightNavigationBars = lightNavigationBarIcons
                }
            }
        }
    }

    Box {
        if (isAndroid14OrAbove) {
            // Paint behind system bars for edge-to-edge effect
            Box(
                Modifier
                    .zIndex(1f)
                    .background(statusBarColor)
                    .windowInsetsTopHeight(WindowInsets.statusBars)
                    .fillMaxWidth()
            )
            Box(
                Modifier
                    .zIndex(1f)
                    .background(navigationBarColor)
                    .windowInsetsBottomHeight(WindowInsets.navigationBars)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
        Box(Modifier.zIndex(2f)) {
            content()
        }
    }
}


@Composable
fun SystemBarsPreview() {
    SystemBars(
        statusBarColor = Color(0xFF0D47A1),
        navigationBarColor = Color(0xFFe5f2fb)
    ) {
        Text("Hello system bars!")
    }
}