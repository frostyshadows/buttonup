package com.sherryyuan.buttonup.drafts.writedraft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.drafts.LocalDraft

class WriteDraftFragment(val dismissListener: WriteDraftContract.DismissListener) :
    WriteDraftContract.View, DialogFragment() {

    override lateinit var presenter: WriteDraftContract.Presenter

    private lateinit var subjectText: TextInputEditText
    private lateinit var bodyText: TextInputEditText
    private lateinit var saveButton: Button
    private lateinit var sendButton: Button
    private lateinit var closeButton: ImageButton

    override fun onStart() {
        super.onStart()

        presenter = WriteDraftPresenter(this)
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_write_draft, container, false).apply {
            subjectText = findViewById(R.id.subject_text)
            bodyText = findViewById(R.id.body_text)
            saveButton = findViewById(R.id.save_button)
            setupSaveButton()
            sendButton = findViewById(R.id.send_button)
            closeButton = findViewById(R.id.close_button)
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

    private fun setupSaveButton() {
        saveButton.setOnClickListener {
            val draft = LocalDraft(subjectText.text.toString(), bodyText.text.toString())
            presenter.saveDraft(draft)
        }
    }

    private fun setupCloseButton() {
        closeButton.setOnClickListener {
            // TODO: Add a confirmation modal.
            dismiss()
        }
    }
}