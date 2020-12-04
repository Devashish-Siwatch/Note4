 package com.example.note4;
 import androidx.appcompat.app.AppCompatActivity;

 import android.database.Cursor;
 import android.database.sqlite.SQLiteDatabase;
 import android.view.View;
 import android.content.ContentValues;
 import android.os.Bundle;
 import android.widget.ArrayAdapter;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.ListView;
 import android.widget.TextView;
 import android.widget.Toast;

 import java.util.ArrayList;


 public class MainActivity extends AppCompatActivity {
     int button_count=1;
     int i =1;
     DBHelper helper = new DBHelper(this);
     SQLiteDatabase database;

     EditText mess;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
     }







     public void saveData(View view) {


         ListView show =findViewById(R.id.NoteShow);
       /*
        if (button_count>1){
         database.delete("TAB", "_id = ?", new String[]{""+i});
         i++;
       }
        */
         //show.setVisibility(View.GONE);
         if (button_count==1){
             database = helper.getWritableDatabase();

         }

         button_count++;
         mess = (EditText) findViewById(R.id.Note);
         String message = mess.getText().toString();
         ContentValues values = new ContentValues();
         values.put("NOTE", message);
         database.insert("TAB", null, values);
         ArrayList<String>arrayList = new ArrayList<>();







         Cursor cursor = database.rawQuery("SELECT NOTE FROM TAB", new String[]{});

         if (cursor != null) {
             cursor.moveToFirst();
         }
         do {
             String a = cursor.getString(0);

             arrayList.add(a);
         } while (cursor.moveToNext());
        



         ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
         show.setAdapter(adapter);
         //show.setText(builder.toString());



     }

 }
