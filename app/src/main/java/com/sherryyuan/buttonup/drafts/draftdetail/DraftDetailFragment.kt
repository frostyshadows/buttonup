package com.sherryyuan.buttonup.drafts.draftdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.databinding.FragmentDraftDetailBinding
import com.sherryyuan.buttonup.utils.countWords

class DraftDetailFragment : Fragment() {

    private lateinit var binding: FragmentDraftDetailBinding
    private val args: DraftDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDraftDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            subjectText.text = args.subject
            bodyText.text = args.body
            wordCountText.text = wordCountText.context.getString(
                R.string.x_word_count, args.body.countWords()
            )
            modificationDateText.text = args.modificationDate
        }
    }
}