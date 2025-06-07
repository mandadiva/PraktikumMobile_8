package com.example.fileinternal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btnSimpan, btnBaca, btnUbah, btnHapus;
    TextView textView;
    String filename = "contohfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnBaca = findViewById(R.id.btnBaca);
        btnUbah = findViewById(R.id.btnUbah);
        btnHapus = findViewById(R.id.btnHapus);
        textView = findViewById(R.id.textView);

        btnSimpan.setOnClickListener(v -> simpanFile());
        btnBaca.setOnClickListener(v -> bacaFile());
        btnUbah.setOnClickListener(v -> ubahFile());
        btnHapus.setOnClickListener(v -> hapusFile());
    }

    void simpanFile() {
        String isi = editText.getText().toString();
        try {
            FileOutputStream fos = openFileOutput(filename, MODE_APPEND);
            fos.write((isi + "\n").getBytes());
            fos.close();
            Toast.makeText(this, "Simpan berhasil", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal menyimpan file", Toast.LENGTH_SHORT).show();
        }
    }

    void ubahFile() {
        String isiBaru = editText.getText().toString();
        try {
            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            fos.write((isiBaru + "\n").getBytes());
            fos.close();
            Toast.makeText(this, "File berhasil diubah", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal mengubah file", Toast.LENGTH_SHORT).show();
        }
    }

    void bacaFile() {
        try {
            FileInputStream fis = openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            reader.close();
            textView.setText(builder.toString());
        } catch (IOException e) {
            textView.setText("File tidak ditemukan");
        }
    }

    void hapusFile() {
        boolean deleted = deleteFile(filename);
        if (deleted) {
            textView.setText("");
            Toast.makeText(this, "File dihapus", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal menghapus file", Toast.LENGTH_SHORT).show();
        }
    }
}
