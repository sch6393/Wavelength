package sch6393.app.wavelength.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import sch6393.app.wavelength.FullscreenActivity
import sch6393.app.wavelength.MainActivity
import sch6393.app.wavelength.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })

        val buttonStart: Button = root.findViewById(R.id.button_start)
        homeViewModel.text.observe(viewLifecycleOwner, {
            buttonStart.setOnClickListener {
                activity?.let{
                    val intent = Intent(it, FullscreenActivity::class.java)
                    intent.putExtra("RGB", MainActivity.RGB)
//                    intent.putExtra("COLOR", Color.RED)
                    it.startActivity(intent)
                }
            }
        })

        return root
    }
}