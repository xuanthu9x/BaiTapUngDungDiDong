package com.example.mynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ListView listNote;
    AdapterNoteList adapter = null;
    NoteItem publicNote;
    ConnectDOM dom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path = this.getFilesDir().getAbsolutePath() + "/abc.xml";
        dom = new ConnectDOM(path);

        listNote = (ListView) findViewById(R.id.noteList);
        ArrayList<NoteItem> items = dom.ReadByDOM();
        Collections.sort(items, new Comparator<NoteItem>() {
            @Override
            public int compare(NoteItem n1, NoteItem n2) {
                return n2.getId() - n1.getId();
            }
        });
        adapter = new AdapterNoteList(MainActivity.this, R.layout.note_item, items );
        if (adapter.list == null)
            listNote.setAdapter(null);
        else listNote.setAdapter(adapter);

        listNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showMenuItem(view, adapter.list.get(position));
            }
        });
        registerForContextMenu(listNote);
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNew:
                Intent intent = new Intent(MainActivity.this, NoteAppActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("store", "create");
                intent.putExtra("data", bundle);
                startActivity(intent);
                break;
            case R.id.menuAboutUs:
                OpenDialogAbout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMenuItem(View view, NoteItem note) {
        this.publicNote = note;
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_item, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuOpen:
                        Intent intent = new Intent(MainActivity.this, NoteAppActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("store", "save");
                        bundle.putSerializable("note", publicNote);
                        intent.putExtra("data", bundle);
                        startActivity(intent);
                        break;
                    case R.id.menuDelete:
                        OpenDialogDelete(publicNote);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();

    }

    //Dialog
    public void OpenDialogDelete(NoteItem note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete " + note.getTitle());
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dom.DeleteNoteByDOM(publicNote);
                adapter.remove(publicNote);
                Toast.makeText(getApplicationContext(), "Note: '" + publicNote.getTitle() + "' has been deleted!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void OpenDialogAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About us");
        builder.setMessage("Team xx:\n" +
                "3117410231 Nguyễn Công Thành\n" +
                "xxxxxxxxxxx xxxxxxxxxxxx\n" +
                "xxxxxxxxxxx xxxxxxxxxxxx\n" +
                "xxxxxxxxxxx xxxxxxxxxxxx");
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

}
