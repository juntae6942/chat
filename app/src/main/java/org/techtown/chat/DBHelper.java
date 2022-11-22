package org.techtown.chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, int version) {
        super(context, "chatting.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE IF NOT EXISTS MESSAGE (ID INTEGER PRIMARY KEY AUTOINCREMENT, CONTENTS TEXT, REGISTER_DATE TEXT NOT NULL DEFAULT (datetime('now', 'localtime')), MESSAGE_TYPE INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS MESSAGE (ID INTEGER PRIMARY KEY AUTOINCREMENT, CONTENTS TEXT, REGISTER_DATE TEXT, MESSAGE_TYPE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MESSAGE");
        onCreate(db);
    }

    public Message selectOne(long insertedId) {
        Message message = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("message", new String[]{"id", "contents", "register_date", "message_type"}, "id = ?", new String[]{String.valueOf(insertedId)}, null, null, null);
        // 하나의 메시지만 가져오기 때문에 while이 아니라 if문을 사용하여 가져올 데이터 있으면 가져와서 message를 리턴하고, 없으면 null을 리턴한다.
        if (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String contents = cursor.getString(1);
            String registerDate = cursor.getString(2);
            int code = cursor.getInt(3);
            message = new Message(id, contents, registerDate, MessageType.of(code));
        }
        return message;
    }

    /*
        selectOne 메소드를 참고하여 아래 메소드를 완성하시오.
     */
    public List<Message> selectAll() {
        List<Message> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("message",new String[]{"id","contents","register_date","message_type"},null,null,null,null,"register_date");
        Message message = null;
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String contents = cursor.getString(1);
            String registerDate = cursor.getString(2);
            int code = cursor.getInt(3);
            message = new Message(id,contents,registerDate,MessageType.of(code));
            list.add(message);
        }
        return list;
    }

    /**
     * 메시지를 등록한다.
     * @param contents 등록할 메시지
     */
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public String getTime(){
        long mNow = System.currentTimeMillis();
        Date mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    public long insert(String contents, MessageType type) {

        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("INSERT INTO MESSAGE (CONTENTS) VALUES ('" + contents + "')");
        ContentValues values = new ContentValues();
        values.put("contents", contents);
        values.put("register_date",getTime());
        values.put("message_type", type.getCode());
        // 57번 라인과 62번 라인은 같은 동작을 한다.
        return db.insert("message", null, values);
    }

    /**
     * 메시지 아이디에 해당하는 데이터를 삭제한다.
     * @param id 삭제할 아이디
     */
    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DELETE FROM MESSAGE WHERE ID = " + id);
        // 71번 라인과 73번 라인은 가능 동작을 한다.
        db.delete("message", "id = ?", new String[]{String.valueOf(id)});
    }

    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("message", null, null);
    }
}
