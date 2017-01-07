package belajar.app.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import belajar.app.login.Storage.SQLiteHandler;
import belajar.app.login.Utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi session
        db = new SQLiteHandler(this);
        session = new SessionManager(this);
        //cek session
        if (!session.isLoggedIn()) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        btnLogout = (Button) findViewById(R.id.btn_logout);

        HashMap<String, String> test = db.getUserDetails();
        btnLogout.setText("Login as " + test.get("name") + "\nLogout?");

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
