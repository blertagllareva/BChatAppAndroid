package com.android.BluetoothChatApp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class History extends AppCompatActivity implements HistoryAdapterOnClickHandler{

    private RecyclerView mRecyclerView;
    private HistoryAdapter mHistoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_history);
        LoadData();

    }
    private void LoadData()
    {
        int recyclerViewOrientation = LinearLayoutManager.VERTICAL;
        boolean shouldReverseLayout = false;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, recyclerViewOrientation, shouldReverseLayout);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        dbHelper db=new dbHelper(this);
        List<HistoryData> list=db.getHistoryData(this);
        mHistoryAdapter = new HistoryAdapter(this, this,list);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
       // mHistoryAdapter.setHistoryData(list);
        mRecyclerView.setAdapter(mHistoryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.backup:
               CopyDatabase();
                return true;
            case R.id.restore:
                RestoreDatabase();
               LoadData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(HistoryData object) {

    }
    String DB_PATH="/data/data/info.devexchanges.bluetoothchatapp/databases/";
    public void CopyDatabase() {

        Log.e("Databasehealper", "********************************");
        try {
            File f1 = new File(DB_PATH + dbHelper.DATABASE_NAME);
            if (f1.exists()) {

                File f2 = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+ "/Chat_Copy.db");
                f2.createNewFile();
                InputStream in = new FileInputStream(f1);
                OutputStream out = new FileOutputStream(f2);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " in the specified directory.");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        Log.e("Databasehealper", "********************************");

    }

    public void RestoreDatabase() {

        Log.e("Databasehealper", "********************************");
        try {
            File f1 = new File(DB_PATH + dbHelper.DATABASE_NAME);
            if (f1.exists()) {

                File f2 = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+ "/Chat_Copy.db");
                f2.createNewFile();
                InputStream in = new FileInputStream(f2);
                OutputStream out = new FileOutputStream(f1);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " in the specified directory.");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        Log.e("Databasehealper", "********************************");

    }
}
