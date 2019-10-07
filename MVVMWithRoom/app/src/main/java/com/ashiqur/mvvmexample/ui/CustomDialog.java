package com.ashiqur.mvvmexample.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ashiqur.mvvmexample.R;
import com.ashiqur.mvvmexample.db.Note;

public class CustomDialog extends Dialog implements View.OnClickListener {

    private EditText etTitle, etDescription;
    private NumberPicker numberPickerPriority;
    private NoteViewModel noteViewModel;

    private Context parentContext;

    public CustomDialog(@NonNull Context context, NoteViewModel noteViewModel) {
        super(context);
        this.noteViewModel = noteViewModel;
        this.parentContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_add_note);
        etTitle = findViewById(R.id.edit_text_title);
        etDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        ImageButton btnSave = findViewById(R.id.button_save);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(parentContext, "Please insert a title and description", Toast.LENGTH_SHORT).show();
        } else {
            Note note = new Note(title, description, priority);
            noteViewModel.insert(note);
        }
        dismiss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        parentContext = null;
        noteViewModel = null; // free back references to make this class available for GC
    }
}
