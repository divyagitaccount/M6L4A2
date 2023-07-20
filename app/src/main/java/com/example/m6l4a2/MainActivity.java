package com.example.m6l4a2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    // Default file name
    private static final String FILE_NAME = "demo.txt";

    // Buttons to read and write file
    Button read,write;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referred UI components
        read = findViewById(R.id.button_load);
        write = findViewById(R.id.button_save);
        mEditText = findViewById(R.id.edit_text);

        // Buttons on click functionality
        read.setOnClickListener(view -> readfiletoExternalprivate());

        write.setOnClickListener(view -> {
            createfiletoExternalprivate();
            mEditText.setText("");
        });
    }

    private void createfiletoExternalprivate()
    {
        //Make a new file with file location and file name
        File file = new File(getExternalFilesDir(null),FILE_NAME);
        //save file
        save(file);
    }

    private void readfiletoExternalprivate()
    {
        //Read new file and set text into TextView
        File file = new File(getExternalFilesDir(null),FILE_NAME);
        String msg = load(file);
        mEditText.setText(msg);
    }


    public void save(File file) {
        //Get string from EditText to put into file
        String text = mEditText.getText().toString();
        FileOutputStream fileOutputStream = null;

        try {
            // Assign file stream the file
            fileOutputStream = new FileOutputStream(file);

            // Write text to file
            fileOutputStream.write(text.getBytes());

            mEditText.getText().clear();

            // show file location as Toast
            Toast.makeText(this, "Saved file: " + file.getName() + " Path: " + file.getPath(),
                    Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    // close stream
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String load(File file) {
        //take file as input and put into file input stream
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            fileInputStream = new FileInputStream(file);
            int text;

            // while there is a line in our file append it to our string
            while ((text = fileInputStream.read()) != -1) {
                stringBuilder.append((char) text);
            }

            // paste our string to Edit text
            mEditText.setText(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    // close stream
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}