package tech.kaustubhdeshpande.systembars

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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

enum class SystemBarsMode {
    Auto,
    FullControl,
    InsetsOnly
}

@Composable
fun SystemBars(
    statusBarColor: Color,
    navigationBarColor: Color,
    lightStatusBarIcons: Boolean = statusBarColor.luminance() > 0.5,
    lightNavigationBarIcons: Boolean = navigationBarColor.luminance() > 0.5,
    mode: SystemBarsMode = SystemBarsMode.Auto,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val window = (context as? Activity)?.window

    // NEW: Use decorView.fitsSystemWindows to check edge-to-edge
    val isEdgeToEdge = window?.decorView?.fitsSystemWindows == false

    val resolvedMode = when (mode) {
        SystemBarsMode.FullControl -> SystemBarsMode.FullControl
        SystemBarsMode.InsetsOnly -> SystemBarsMode.InsetsOnly
        SystemBarsMode.Auto -> if (isEdgeToEdge) SystemBarsMode.InsetsOnly else SystemBarsMode.FullControl
    }

    val isAndroid14OrAbove = Build.VERSION.SDK_INT >= 34

    LaunchedEffect(
        statusBarColor,
        navigationBarColor,
        lightStatusBarIcons,
        lightNavigationBarIcons,
        resolvedMode,
        isAndroid14OrAbove
    ) {
        window?.let {
            if (resolvedMode == SystemBarsMode.FullControl) {
                if (!isAndroid14OrAbove) {
                    @Suppress("DEPRECATION")
                    it.statusBarColor = statusBarColor.toArgb()
                    @Suppress("DEPRECATION")
                    it.navigationBarColor = navigationBarColor.toArgb()
                    WindowCompat.getInsetsController(it, it.decorView)?.apply {
                        isAppearanceLightStatusBars = lightStatusBarIcons
                        isAppearanceLightNavigationBars = lightNavigationBarIcons
                    }
                } else {
                    @Suppress("DEPRECATION")
                    it.statusBarColor = android.graphics.Color.TRANSPARENT
                    @Suppress("DEPRECATION")
                    it.navigationBarColor = android.graphics.Color.TRANSPARENT
                    WindowCompat.getInsetsController(it, it.decorView)?.apply {
                        isAppearanceLightStatusBars = lightStatusBarIcons
                        isAppearanceLightNavigationBars = lightNavigationBarIcons
                    }
                }
                WindowCompat.setDecorFitsSystemWindows(it, false)
            } // InsetsOnly: overlays handled below
        }
    }

    Box {
        if (isAndroid14OrAbove || resolvedMode == SystemBarsMode.InsetsOnly) {
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
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Hello, System Bars!",
                color = Color.Black
            )
        }
    }
}