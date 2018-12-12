package com.stetter.dhartmuseum.data.local;

import android.content.Context;

import com.stetter.dhartmuseum.data.dao.ObjectDAO;
import com.stetter.dhartmuseum.data.database.Database;
import com.stetter.dhartmuseum.model.Record;

import java.util.List;

import io.reactivex.Flowable;

public class ObjectLocalRepository {

    // Pega os dados da base de dados
    public Flowable<List<Record>> getLocalRecords(Context context) {
        Database room = Database.getDatabase(context);
        ObjectDAO resultsDao = room.objectDAO();
        return resultsDao.getAll();
    }

    // Insere uma lista reults na base de dados
    public void insertItems(Context context, List<Record> records) {
        Database room = Database.getDatabase(context);
        ObjectDAO resultsDao = room.objectDAO();
        resultsDao.insert(records);
    }
}

