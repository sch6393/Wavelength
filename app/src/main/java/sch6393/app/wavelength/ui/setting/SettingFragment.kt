package sch6393.app.wavelength.ui.setting

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import sch6393.app.wavelength.R
import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt

class SettingFragment : Fragment() {

    private val gamma = 0.80
    private val intensityMax = 255.0

    // 해결 못함 : SeekBar 최소값을 0으로 하지 않으면 트래킹이 되지 않음
    // 위 문제로 인해 보정 값을 둠
    private val wlCorrection = 380

    private lateinit var settingViewModel: SettingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        settingViewModel =
                ViewModelProvider(this).get(SettingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_setting, container, false)

        val textViewWL: TextView = root.findViewById(R.id.text_wl)

        val seekBar: SeekBar = root.findViewById(R.id.seekBar_wl)
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                // ツマミがドラッグされると呼ばれる
                override fun onProgressChanged(
                    seekBar: SeekBar, i: Int, b: Boolean,
                ) {
                    val rgb = calcRGB(((i + wlCorrection).toDouble()))
                    val str = String.format(Locale.US, "%d nm", i + wlCorrection)
                    textViewWL.text = str
                    textViewWL.background = Color.rgb(rgb[0], rgb[1], rgb[2]).toDrawable()
                    Log.d("Test", "r = " + rgb[0] + "  g = " + rgb[1] + "  b = " + rgb[2])
                }

                // ツマミがタッチされた時に呼ばれる
                override fun onStartTrackingTouch(seekBar: SeekBar) {
//                    Toast.makeText(activity, "Start Tracking", Toast.LENGTH_SHORT).show()
                }

                // ツマミがリリースされた時に呼ばれる
                override fun onStopTrackingTouch(seekBar: SeekBar) {
//                    Toast.makeText(activity, "Stop Tracking", Toast.LENGTH_SHORT).show()
                }
            })

        return root
    }

    /**
     * Taken from Earl F. Glynn's web page:
     * [Spectra Lab Report](http://www.efg2.com/Lab/ScienceAndEngineering/Spectra.htm)
     */
    private fun calcRGB(wl: Double): IntArray {
        val r: Double
        val g: Double
        val b: Double

        if (wl >= 380 && wl < 440) {
            r = -(wl - 440) / (440 - 380)
            g = 0.0
            b = 1.0
        } else if (wl >= 440 && wl < 490) {
            r = 0.0
            g = (wl - 440) / (490 - 440)
            b = 1.0
        } else if (wl >= 490 && wl < 510) {
            r = 0.0
            g = 1.0
            b = -(wl - 510) / (510 - 490)
        } else if (wl >= 510 && wl < 580) {
            r = (wl - 510) / (580 - 510)
            g = 1.0
            b = 0.0
        } else if (wl >= 580 && wl < 645) {
            r = 1.0
            g = -(wl - 645) / (645 - 580)
            b = 0.0
        } else if (wl >= 645 && wl < 781) {
            r = 1.0
            g = 0.0
            b = 0.0
        } else {
            r = 0.0
            g = 0.0
            b = 0.0
        }

        // Let the intensity fall off near the vision limits
        val factor: Double = if (wl >= 380 && wl < 420) {
            0.3 + 0.7 * (wl - 380) / (420 - 380)
        } else if (wl >= 420 && wl < 701) {
            1.0
        } else if (wl >= 701 && wl < 781) {
            0.3 + 0.7 * (780 - wl) / (780 - 700)
        } else {
            0.0
        }

        val rgb = IntArray(3)

        // Don't want 0^x = 1 for x <> 0
        rgb[0] = if (r == 0.0) 0 else (intensityMax * (r * factor).pow(gamma)).roundToInt()
        rgb[1] = if (g == 0.0) 0 else (intensityMax * (g * factor).pow(gamma)).roundToInt()
        rgb[2] = if (b == 0.0) 0 else (intensityMax * (b * factor).pow(gamma)).roundToInt()

        return rgb
    }
}