package com.sherryyuan.buttonup.drafts.writedraft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.databinding.FragmentWriteDraftBinding
import com.sherryyuan.buttonup.drafts.LocalDraft

class WriteDraftFragment(private val dismissListener: WriteDraftContract.DismissListener) :
    WriteDraftContract.View, DialogFragment() {

    override lateinit var presenter: WriteDraftContract.Presenter

    private lateinit var binding: FragmentWriteDraftBinding

    override fun onStart() {
        super.onStart()

        presenter = WriteDraftPresenter(this)
        presenter.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteDraftBinding.inflate(inflater, container, false)
        return binding.root.also {
            setupEditText()
            setupSaveButton()
            setupCloseButton()
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun dismiss() {
        dismissListener.onWriteDraftDismissed()
        super.dismiss()
    }

    override fun onDraftSaved() {
        Toast.makeText(context, "Draft saved", Toast.LENGTH_SHORT).show()
        dismiss()
    }

    private fun setupEditText() {
        binding.apply {
            bodyText.addTextChangedListener { editable ->
                val wordCount: Int =
                    editable.toString().split(" *").filter { it.isNotBlank() }.count()
                wordCountText.text = HtmlCompat.fromHtml(
                    wordCountText.context.getString(R.string.word_count, wordCount),
                    FROM_HTML_MODE_COMPACT
                )
            }
        }
    }

    private fun setupSaveButton() {
        binding.apply {
            saveButton.setOnClickListener {
                val draft =
                    LocalDraft(
                        subjectText.text.toString(),
                        bodyText.text.toString()
                    )
                presenter.saveDraft(draft)
            }
        }
    }

    private fun setupCloseButton() {
        binding.closeButton.setOnClickListener {
            // TODO: Add a confirmation modal.
            dismiss()
        }
    }
}