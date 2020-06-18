package com.example.mybeamin.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mybeamin.model.menu;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface menuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertmenu(menu menu);
    @Delete
    void deletemenu(menu menu);
    @Update
    void updatemenu(menu menu);
    @Query("SELECT * FROM menus")
   List<menu> getMenus();
    @Query("SELECT * FROM menus WHERE  Idmenu =:Id")
    menu getMenu(int Id);
}
