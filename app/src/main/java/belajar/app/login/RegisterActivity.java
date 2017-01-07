package belajar.app.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import belajar.app.login.Storage.SQLiteHandler;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtFullName, edtUsername, edtPassword;
    private Button btnRegister, btnBackToHome;

    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialization of SQLiteHandler class
        db = new SQLiteHandler(this);

        //casting variable to object
        edtFullName = (EditText) findViewById(R.id.edt_fullname);
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        btnRegister = (Button) findViewById(R.id.btn_register);
        btnBackToHome = (Button) findViewById(R.id.btn_back_home);

        //event button
        btnRegister.setOnClickListener(this);
        btnBackToHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent backHome = new Intent(RegisterActivity.this, LoginActivity.class);
        switch (v.getId()) {
            case R.id.btn_back_home:
                //disini kodenya untuk back to home
                startActivity(backHome);
                finish();
                break;
            case R.id.btn_register:
                //disini kodenya untuk tombol register
                String fullName = edtFullName.getText().toString();
                String userName = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                if (!fullName.isEmpty() && !userName.isEmpty() && !password.isEmpty()) {
                    db.addUser(fullName, userName, password);
                    Toast.makeText(this,
                            "Registrasi Berhasil",
                            Toast.LENGTH_SHORT).show();
                    startActivity(backHome);
                    finish();
                } else {
                    Toast.makeText(this,
                            "Salah satu field tidak boleh kosong",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
