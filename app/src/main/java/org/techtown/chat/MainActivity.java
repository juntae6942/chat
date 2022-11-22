package org.techtown.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    List<Message> list;
    EditText editText;
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    /*
    문제에 맞게 onCreate 메소드를 정의하시오.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // DBHelper 객체를 생성하여 테이블 생성 및 데이터 가져오기
        dbHelper = new DBHelper(this, 1);

        Button sendButton = findViewById(R.id.sendButton);//전송 버튼

        editText = findViewById(R.id.contentsEdit);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    sendButton.setEnabled(false);
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    sendButton.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()>0){
                    sendButton.setEnabled(true);
                }
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAction(view);
                editText.setText("");
            }
        });
        list = dbHelper.selectAll();

        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        messageAdapter = new MessageAdapter(list);
        recyclerView.setAdapter(messageAdapter);
    }
    /*
        전송 버튼을 누를 때 동작 되는 메소드
        이 메소드 내용 작성
    */
    public void sendAction(View view){
        String ms = editText.getText().toString();
        long id = dbHelper.insert(ms,MessageType.RIGHT_CONTENTS);
        list.add(dbHelper.selectOne(id));
        messageAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(list.size());
    }
}