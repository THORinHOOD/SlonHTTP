package catwithbowtie.slonhttp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import okhttp3.*;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private EditText name;

    private final OkHttpClient client = new OkHttpClient();

    public static final String ip = "http://92.63.105.60";
    public static final int port = 7070;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);

        activity = this;
    }

    public void registration(View view) {
        Request request = new Request.Builder()
                .url(ip + ":" + port + "/reg?" + "name=" + name.getText().toString() + "&login=" + login.getText().toString() + "&password=" + password.getText().toString()).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                final String result = response.body().string();

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, result, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}
