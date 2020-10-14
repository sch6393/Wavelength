package sch6393.app.wavelength.ui.info

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import sch6393.app.wavelength.R


class InfoFragment : Fragment() {

    private val start = 77
    private val end = 80
    private lateinit var infoViewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoViewModel =
                ViewModelProvider(this).get(InfoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_info, container, false)
        val textView: TextView = root.findViewById(R.id.text_info)
        infoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val textView2: TextView = root.findViewById(R.id.text_info2)
        infoViewModel.text2.observe(viewLifecycleOwner, Observer {
            val spannableStringBuilder = SpannableStringBuilder(it)
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(Color.BLUE),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableStringBuilder.setSpan(
                StyleSpan(Typeface.BOLD),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            textView2.text = spannableStringBuilder
        })

        val textView3: TextView = root.findViewById(R.id.text_info3)
        infoViewModel.text3.observe(viewLifecycleOwner, Observer {
            textView3.text = it
            textView3.setTextColor(Color.RED)
        })

        return root
    }
}