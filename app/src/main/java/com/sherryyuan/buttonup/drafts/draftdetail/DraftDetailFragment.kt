package com.sherryyuan.buttonup.drafts.draftdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sherryyuan.buttonup.databinding.FragmentDraftDetailBinding

class DraftDetailFragment : Fragment() {

    private lateinit var binding: FragmentDraftDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDraftDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

}