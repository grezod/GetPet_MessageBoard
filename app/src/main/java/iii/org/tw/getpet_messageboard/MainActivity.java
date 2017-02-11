package iii.org.tw.getpet_messageboard;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
//***********

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//************
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

//***CLASS與LAYOUT配對
/*
MainActivity.java + activity_main2.xml(裡面包content_main2.xml
page_addNewMessage.java + activity_page_add_new_message.xml

*/
//**

public class MainActivity extends AppCompatActivity {
     static   MainActivity iv_MainActivity ;
    //**
    ArrayList<object_Get_messageOfMessageBoard> iv_ArrayList_object_Get_messageOfMessageBoard;
    //**

    object_petDataForSelfDB iv_TEST_object_petDataForSelfDB = new object_petDataForSelfDB();
    List<object_Get_messageOfMessageBoard> iv_object_Get_messageOfMessageBoard = new ArrayList<object_Get_messageOfMessageBoard>();
    private ListView iv_listView_listViewOfMessageBoard;
    private AdapterOfMessageBoard iv_Adapter_AdapterOfMessageBoard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }
    public void init(){
        iv_MainActivity=this;
        setViewComponent();
        testPetData();
        //**
        query查詢該寵物所有留言();



    }


    private void setViewComponent() {
        fab_sendNewMessage = (FloatingActionButton)findViewById(R.id.fab_sendNewMessage);
        fab_sendNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l_intent = new Intent(MainActivity.this,page_addNewMessage.class);
                l_intent.putExtra("object_petDataForSelfDB",iv_TEST_object_petDataForSelfDB);
                startActivityForResult(l_intent,CDictionary.requestCodeOfAddNewMessage);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CDictionary.requestCodeOfAddNewMessage && resultCode == RESULT_OK){
            MainActivity.iv_MainActivity.finish();
        }
    }



    private void testPetData() {
        iv_TEST_object_petDataForSelfDB.setAnimalID(1);

    }


    public void query查詢該寵物所有留言(){

            //***
            OkHttpClient l_OkHttp_client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://twpetanimal.ddns.net:9487/api/v1/boards/"+iv_TEST_object_petDataForSelfDB.getAnimalID())
                    .addHeader("Content-Type", "application/json")
                    .get()
                    .build();

            Call call = l_OkHttp_client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("http", "fail");

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    iv_ArrayList_object_Get_messageOfMessageBoard = new ArrayList<object_Get_messageOfMessageBoard>();
                    final String json = response.body().string();
                    Log.d("http", json);



                    //textView.setText(json);
                    Gson gson = new Gson();

                    iv_ArrayList_object_Get_messageOfMessageBoard =  gson.fromJson(json,new TypeToken<ArrayList<object_Get_messageOfMessageBoard>>(){}.getType());
                    Log.d("http2", iv_ArrayList_object_Get_messageOfMessageBoard.get(0).getUserName().toString());


                    //**

                    if(iv_ArrayList_object_Get_messageOfMessageBoard.size() == 0){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(MainActivity.this,"此寵物目前沒有任何留言",Toast.LENGTH_LONG).show();

                            }
                        });

                        return;
                    }

                    //Log.d("http", iv_object_petDataForSelfDB.toString());



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i=0;i<iv_ArrayList_object_Get_messageOfMessageBoard.size();i +=1) {
                                iv_object_Get_messageOfMessageBoard.add(iv_ArrayList_object_Get_messageOfMessageBoard.get(i));
                            }
                            create產生LIST畫面();
                        }
                    });


                }
            });

        }

    private void create產生LIST畫面() {
        //********
        iv_listView_listViewOfMessageBoard = (ListView)findViewById(R.id.ListView_for_messageBoard);
        iv_listView_listViewOfMessageBoard.setAdapter(new AdapterOfMessageBoard(this,iv_object_Get_messageOfMessageBoard));
        //點擊清單事件
        iv_listView_listViewOfMessageBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*

                Toast.makeText(MainActivity.this,"position: "+position+"  :  long : "+id,Toast.LENGTH_LONG).show();
                Intent l_intent = new Intent(MainActivity.this,page_editPetData.class);
                object_petDataForSelfDB l_object_petDataForSelfDB =iv_object_petDataForSelfDB[position];
                l_intent.putExtra("object_ConditionOfAdoptPet_objA",l_object_petDataForSelfDB );
                //Log.d("",iv_object_petDataForSelfDB[0].getAnimalName());
               startActivityForResult(l_intent,CDictionary.IntentRqCodeOfOpenEditPetData);
                finish();
                */

            }
        });
        //*******
        iv_Adapter_AdapterOfMessageBoard = new AdapterOfMessageBoard(this,iv_object_Get_messageOfMessageBoard);
        iv_listView_listViewOfMessageBoard.setAdapter(iv_Adapter_AdapterOfMessageBoard);
    }


FloatingActionButton fab_sendNewMessage;


}
