package com.zero.konveksiku.data.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.zero.konveksiku.data.model.user.DataClient;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ClientDao_Impl implements ClientDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DataClient> __insertionAdapterOfDataClient;

  private final EntityDeletionOrUpdateAdapter<DataClient> __deletionAdapterOfDataClient;

  private final EntityDeletionOrUpdateAdapter<DataClient> __updateAdapterOfDataClient;

  public ClientDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDataClient = new EntityInsertionAdapter<DataClient>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `client` (`nama_client`,`id_client`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataClient value) {
        if (value.getNama_client() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNama_client());
        }
        if (value.getId_client() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getId_client());
        }
      }
    };
    this.__deletionAdapterOfDataClient = new EntityDeletionOrUpdateAdapter<DataClient>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `client` WHERE `id_client` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataClient value) {
        if (value.getId_client() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId_client());
        }
      }
    };
    this.__updateAdapterOfDataClient = new EntityDeletionOrUpdateAdapter<DataClient>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `client` SET `nama_client` = ?,`id_client` = ? WHERE `id_client` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataClient value) {
        if (value.getNama_client() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNama_client());
        }
        if (value.getId_client() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getId_client());
        }
        if (value.getId_client() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getId_client());
        }
      }
    };
  }

  @Override
  public void addClinet(final DataClient data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDataClient.insert(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final DataClient data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDataClient.handle(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final DataClient data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDataClient.handle(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<DataClient>> getClient() {
    final String _sql = "SELECT * FROM client ORDER BY nama_client ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"client"}, false, new Callable<List<DataClient>>() {
      @Override
      public List<DataClient> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNamaClient = CursorUtil.getColumnIndexOrThrow(_cursor, "nama_client");
          final int _cursorIndexOfIdClient = CursorUtil.getColumnIndexOrThrow(_cursor, "id_client");
          final List<DataClient> _result = new ArrayList<DataClient>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DataClient _item;
            final String _tmpNama_client;
            _tmpNama_client = _cursor.getString(_cursorIndexOfNamaClient);
            final Integer _tmpId_client;
            if (_cursor.isNull(_cursorIndexOfIdClient)) {
              _tmpId_client = null;
            } else {
              _tmpId_client = _cursor.getInt(_cursorIndexOfIdClient);
            }
            _item = new DataClient(_tmpNama_client,_tmpId_client);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
