package com.example.surai.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.surai.myapplication.model.LoginResponse;
import com.example.surai.myapplication.model.Status;
import com.example.surai.myapplication.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.login_module_title);
        setSupportActionBar(myToolbar);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Log.d("TAG", "onCreate: User exist. Redirecting...");
            Intent intent = new Intent(this, ListingModuleActivity.class);
            startActivity(intent);
        }

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final EditText etUserName = (EditText) findViewById(R.id.etUserName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<LoginResponse> call = RetrofitClient.getInstance().getApi().login(etUserName.getText().toString(), etPassword.getText().toString());

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        try {
                            LoginResponse loginResponse = response.body();
                            Log.d("TAG", "onClick: This is working.\nUserName: " + etUserName.getText().toString() + "\nPassword: " + etPassword.getText().toString()
                                    + "\ntoken: " + loginResponse.getToken() + "\nmessage: " + loginResponse.getStatus().getMessage());

                            Status status = loginResponse.getStatus();
                            int code = status.getCode();

                            if (code == 200) {
                                SharedPrefManager.getInstance(MainActivity.this)
                                        .saveUser(loginResponse.getId(), loginResponse.getToken());
                                etPassword.setText("");
                                etUserName.setText("");

                                Intent downloadIntent = new Intent(MainActivity.this, ListingModuleActivity.class);
                                startActivity(downloadIntent);
                            } else {
                                Toast.makeText(MainActivity.this, "" + status.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.d("TAG", "onClick: This is not working.");
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}
