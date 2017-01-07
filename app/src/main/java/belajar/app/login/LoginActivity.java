package belajar.app.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import belajar.app.login.Storage.SQLiteHandler;
import belajar.app.login.Utils.SessionManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private TextView btnRegisterAccount;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inisialisasi
        db = new SQLiteHandler(this);
        session = new SessionManager(this);

        //cek session
        if (session.isLoggedIn()) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        //casting variable untuk object dengan id tertentu.
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegisterAccount = (TextView) findViewById(R.id.btn_register_account);

        btnLogin.setOnClickListener(this);
        btnRegisterAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //aksi tombol btn_login disini..
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                HashMap<String, String> test;
                test = db.getUserDetails();

                if (!username.equals("") && !password.equals("")) {
                    if (username.equals(test.get("username"))) {
                        if (password.equals(test.get("password"))) {
                            session.setLogin(true);
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(this, "Password salah.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Username tidak terdaftar.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Username dan password tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register_account:
                Intent reg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(reg);
                finish();
                break;
        }
    }
}
