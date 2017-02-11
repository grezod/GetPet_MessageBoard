package iii.org.tw.getpet_messageboard;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static iii.org.tw.getpet_messageboard.R.id.btnSendMessage;
import static iii.org.tw.getpet_messageboard.R.id.edTxt_message;

public class page_addNewMessage extends Activity {
    public static final MediaType Iv_MTyp_JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_add_new_message);
        init();
    }

    private void init() {
        edTxt_message = (EditText)findViewById(R.id.edTxt_message);
        btnSendMessage = (Button)findViewById(R.id.btnSendMessage);
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //**
                String l_string_未填寫的欄位有哪些 = check確認是否欄位都有填寫();

                if (l_string_未填寫的欄位有哪些.length() > 10) {
                    Toast.makeText(page_addNewMessage.this,"您尚未填寫留言內容喔",Toast.LENGTH_LONG).show();
                }else {
                    sendMessae();
                }
                //**



            }
        });
    }
    public String check確認是否欄位都有填寫() {

        //************
        String p_string_未填寫的欄位有哪些 = "尚未填寫留言內容:\n";
        Log.d("原始長度", p_string_未填寫的欄位有哪些.length() + "");
        //*********
        p_string_未填寫的欄位有哪些 += edTxt_message.getText().toString().isEmpty() ? "   " : "";


        return p_string_未填寫的欄位有哪些;
    }

    private void sendMessae() {
        final CountDownLatch l_CountDownLatch = new CountDownLatch(1);
        //**
        Intent l_intent = getIntent();
        object_petDataForSelfDB l_object_petDataForSelfDB = ((object_petDataForSelfDB)l_intent.getSerializableExtra("object_petDataForSelfDB"));
        object_Post_messageOfMessageBoard l_object_Post_messageOfMessageBoard = new object_Post_messageOfMessageBoard();
        //***
        l_object_Post_messageOfMessageBoard.setBoard_animalID(l_object_petDataForSelfDB.getAnimalID());
        l_object_Post_messageOfMessageBoard.setBoard_userID("d185f920-68fe-48e5-b3d3-bea66f403f3e");//須帶入目前登錄者id
        l_object_Post_messageOfMessageBoard.setBoardContent(edTxt_message.getText().toString());
        //***
        //****************
        Gson l_gsn_gson = new Gson();
        String l_JSONString_object_Post_messageOfMessageBoard = l_gsn_gson.toJson(l_object_Post_messageOfMessageBoard);
        //***********
        RequestBody requestBody = RequestBody.create(Iv_MTyp_JSON, l_JSONString_object_Post_messageOfMessageBoard);

        //***************
        Request request = new Request.Builder()
                .url("http://twpetanimal.ddns.net:9487/api/v1/boards")
                .addHeader("Content-Type", "raw")
                .post(requestBody)
                .build();
        OkHttpClient Iv_OkHttp_client = new OkHttpClient();

        Call call = Iv_OkHttp_client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("http", "fail");

            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                Log.d("http", json);
                //textView.setText(json);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jObj = new JSONObject(json);

                            //Toast.makeText(page_editPetData.this, "此筆資料的id: " + id + ")", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
                l_CountDownLatch.countDown();

                //parseJson(json);
            }
        });

        try {
            l_CountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //*******************

        l_intent = new Intent(this, MainActivity.class);
        startActivity(l_intent);

        finish();





    }

    EditText edTxt_message;
    Button btnSendMessage;
}
