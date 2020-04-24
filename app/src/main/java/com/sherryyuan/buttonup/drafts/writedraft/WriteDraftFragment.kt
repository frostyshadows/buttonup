package com.sherryyuan.buttonup.drafts.writedraft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.sherryyuan.buttonup.R
import com.sherryyuan.buttonup.drafts.LocalDraft

class WriteDraftFragment : WriteDraftContract.View, Fragment() {

    override lateinit var presenter: WriteDraftContract.Presenter

    private lateinit var subjectText: TextInputEditText
    private lateinit var bodyText: TextInputEditText
    private lateinit var saveButton: Button
    private lateinit var sendButton: Button

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
        }
    }

    override fun onStop() {
        super.onStop()

        presenter.stop()
    }

    override fun onDraftSaved() {
        Toast.makeText(context, "Draft saved", Toast.LENGTH_SHORT).show()

    }

    private fun setupSaveButton() {
        saveButton.setOnClickListener {
            val draft = LocalDraft(subjectText.text.toString(), bodyText.text.toString())
            presenter.saveDraft(draft)
        }
    }
}