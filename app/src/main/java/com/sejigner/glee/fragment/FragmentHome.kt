package com.sejigner.glee.fragment

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sejigner.glee.CanvasActivity
import com.sejigner.glee.EditTextActivity
import com.sejigner.glee.R
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : Fragment() {

    private lateinit var rbCafe24: TextView
    private lateinit var rbAritaBuri: TextView
    private lateinit var rbMapoFlowerIsland: TextView
    private lateinit var rbHambakSnow: TextView
    private lateinit var tvNewTranscription: TextView

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rbCafe24 = view.findViewById(R.id.rb_cafe24SurroundAir)
        rbAritaBuri = view.findViewById(R.id.rb_aritaBuri)
        rbMapoFlowerIsland = view.findViewById(R.id.rb_mapoFlowerIsland)
        rbHambakSnow = view.findViewById(R.id.rb_hambaksnow)
        tvNewTranscription = view.findViewById(R.id.tv_new_transcription)

        rbCafe24.setOnClickListener {
            tv_work_example.typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/cafe24_surround_air.ttf")
        }
        rbAritaBuri.setOnClickListener {
            tv_work_example.typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/arita_buri.otf")
        }
        rbMapoFlowerIsland.setOnClickListener {
            tv_work_example.typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/mapo_flower_island.ttf")
        }

        rbHambakSnow.setOnClickListener {
            tv_work_example.typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/hambaksnow.ttf")
        }

        tvNewTranscription.setOnClickListener {
            val intent = Intent(activity, EditTextActivity::class.java)
            startActivity(intent)
        }
    }

}