package sch6393.app.wavelength.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import sch6393.app.wavelength.R

class InfoFragment : Fragment() {

    private lateinit var infoViewModel: InfoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        infoViewModel =
                ViewModelProvider(this).get(InfoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_info, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        infoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}