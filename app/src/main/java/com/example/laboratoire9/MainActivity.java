package com.example.laboratoire9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    final String URL_POINT_ENTREE = "http://10.0.2.2:3000";
    final String TAG = "MesMessages";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"Build ok");

        (new Thread() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();

                // Lecture
                Request request = new Request.Builder()
                        .url(URL_POINT_ENTREE + "/comptes")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    ResponseBody responseBody = response.body();
                    String jsonData = responseBody.string();
                    Log.i(TAG, "REPONSE :\n"+jsonData);
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }

                // Ecriture
                try {
                    MediaType JSON = MediaType.parse("application/json; chartset=utf-8");
                    CompteBancaire cpt1 = new CompteBancaire("AXD5463WR", "Jean");
                    JSONObject obj = new JSONObject();
                    obj.put("numero", cpt1.getNumero());
                    obj.put("titulaire", cpt1.getTitulaire());
                    obj.put("solde", cpt1.getSolde());
                    RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);

                    request = new Request.Builder().url(URL_POINT_ENTREE + "/comptes")
                            .post(corpsRequete)
                            .build();

                    Response response = okHttpClient.newCall(request).execute();
                    if (response.code() == 201) Log.i(TAG, "Compte inséré avec succès");
                    else Log.i(TAG, "Compte non inséré");
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        }).start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}