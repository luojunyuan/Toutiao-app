package com.example.toutiaoapplication.ui.announce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toutiaoapplication.R

class AnnounceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_announce, container, false)
    }

    companion object {
        const val TAG = "AnnounceFragment"
    }
}