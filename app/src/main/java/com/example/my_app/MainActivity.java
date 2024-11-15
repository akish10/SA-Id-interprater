package com.example.my_app;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;

    private DatabaseHelper databaseHelper;

    ImageView upload, download;
    EditText uploadText, downloadText;
    Button downloadBtn, uploadBtn;
    private Uri Uri_selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this); // Initialize the DatabaseHelper

        upload = findViewById(R.id.image_upload);
        download = findViewById(R.id.image_download);
        uploadText = findViewById(R.id.description_upload);
        downloadText = findViewById(R.id.description_download);
        downloadBtn = findViewById(R.id.image_download_btn);
        uploadBtn = findViewById(R.id.image_upload_btn);

        upload.setOnClickListener(this);
        download.setOnClickListener(this);
        downloadBtn.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_upload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;

            case R.id.image_upload_btn:
                String description = uploadText.getText().toString();
                if (Uri_selectedImage != null) {
                    saveImageToDatabase(description, Uri_selectedImage.toString());
                }
                break;

            case R.id.image_download:
                // Placeholder for future implementation
                break;

            case R.id.image_download_btn:
                // Placeholder for future implementation
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri_selectedImage = data.getData(); // Update Uri_selectedImage here
            upload.setImageURI(Uri_selectedImage); // Display the selected image
        }
    }

    // Method to save image data to the database
    private void saveImageToDatabase(String description, String imageUri) {
        long result = databaseHelper.insertImageData(description, imageUri);
        if (result != -1) {
            Toast.makeText(this, "Image saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error saving image.", Toast.LENGTH_SHORT).show();
        }
    }

    // Example method to load images from the database
    private void loadImagesFromDatabase() {
        Cursor cursor = databaseHelper.getAllImages();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION));
                String imageUri = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE_URI));
                // Use these values as needed in the UI.
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
    }
}

/*package com.example.my_app;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;

    private DatabaseHelper databaseHelper;

    ImageView upload, download;
    EditText uploadText, downloadText;
    Button downloadBtn, uploadBtn;
    private Uri Uri_selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upload = findViewById(R.id.image_upload);
        download = findViewById(R.id.image_download);
        uploadText = findViewById(R.id.description_upload);
        downloadText = findViewById(R.id.description_download);
        downloadBtn = findViewById(R.id.image_download_btn);
        uploadBtn = findViewById(R.id.image_upload_btn);

        upload.setOnClickListener(this);
        download.setOnClickListener(this);
        downloadBtn.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.image_upload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;

            case R.id.image_download:
                // Placeholder for future implementation
                break;

            case R.id.image_upload_btn:
                // Placeholder for future implementation
                break;

            case R.id.image_download_btn:
                // Placeholder for future implementation
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            upload.setImageURI(selectedImage);
        }
    }

    // Method to save image data to database
    private void saveImageToDatabase(String description, String imageUri) {
        long result = databaseHelper.insertImageData(description, imageUri);
        if (result != -1) {
            Toast.makeText(this, "Image saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error saving image.", Toast.LENGTH_SHORT).show();
        }
    }

    // Example: Saving image when upload button is clicked
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.image_upload):
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;

            case (R.id.image_upload_btn):
                String description = uploadText.getText().toString();
                if (Uri_selectedImage != null) {
                    saveImageToDatabase(description, Uri_selectedImage.toString());
                }
                break;
        }
    }

    private void loadImagesFromDatabase() {
        Cursor cursor = databaseHelper.getAllImages();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION));
                String imageUri = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE_URI));
                // You can now use these values, for example, to display in your UI.
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
    }

}


/*package com.example.my_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;

    ImageView upload, download;

    EditText uploadText , downloadText;

    Button downloadBtn, uploadBtn;
    private Uri Uri_selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        upload = (ImageView) findViewById(R.id.image_upload);

        download = (ImageView) findViewById(R.id.image_download);

        uploadText = (EditText) findViewById(R.id.description_upload);

        downloadText = (EditText) findViewById(R.id.description_download);

        downloadBtn = (Button) findViewById(R.id.image_download_btn);

        uploadBtn = (Button) findViewById(R.id.image_upload_btn);

        upload.setOnClickListener(this);

        download.setOnClickListener(this);

        downloadBtn.setOnClickListener(this);

        uploadBtn.setOnClickListener(this);


    }

    @Override

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.image_upload;

            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);

               break;

            case R.id.image_download;

               break;

            case R.id.image_upload_btn;

               break;

            case R.id.image_download_btn;

               break;
        }
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode , Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){

            Uri selectedImage = data.getData();

            upload.setImageURI(selectedImage);
        }
    }
}*/