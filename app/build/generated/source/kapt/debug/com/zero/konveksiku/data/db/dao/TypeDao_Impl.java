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
import com.zero.konveksiku.data.model.user.DataType;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TypeDao_Impl implements TypeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DataType> __insertionAdapterOfDataType;

  private final EntityDeletionOrUpdateAdapter<DataType> __deletionAdapterOfDataType;

  private final EntityDeletionOrUpdateAdapter<DataType> __updateAdapterOfDataType;

  public TypeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDataType = new EntityInsertionAdapter<DataType>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `type` (`nama_type`,`id_type`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataType value) {
        if (value.getNama_type() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNama_type());
        }
        if (value.getId_type() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getId_type());
        }
      }
    };
    this.__deletionAdapterOfDataType = new EntityDeletionOrUpdateAdapter<DataType>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `type` WHERE `id_type` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataType value) {
        if (value.getId_type() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId_type());
        }
      }
    };
    this.__updateAdapterOfDataType = new EntityDeletionOrUpdateAdapter<DataType>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `type` SET `nama_type` = ?,`id_type` = ? WHERE `id_type` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataType value) {
        if (value.getNama_type() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNama_type());
        }
        if (value.getId_type() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getId_type());
        }
        if (value.getId_type() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getId_type());
        }
      }
    };
  }

  @Override
  public void addType(final DataType data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDataType.insert(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final DataType data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDataType.handle(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final DataType data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDataType.handle(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<DataType>> getType() {
    final String _sql = "SELECT * FROM type";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"type"}, false, new Callable<List<DataType>>() {
      @Override
      public List<DataType> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNamaType = CursorUtil.getColumnIndexOrThrow(_cursor, "nama_type");
          final int _cursorIndexOfIdType = CursorUtil.getColumnIndexOrThrow(_cursor, "id_type");
          final List<DataType> _result = new ArrayList<DataType>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DataType _item;
            final String _tmpNama_type;
            _tmpNama_type = _cursor.getString(_cursorIndexOfNamaType);
            final Integer _tmpId_type;
            if (_cursor.isNull(_cursorIndexOfIdType)) {
              _tmpId_type = null;
            } else {
              _tmpId_type = _cursor.getInt(_cursorIndexOfIdType);
            }
            _item = new DataType(_tmpNama_type,_tmpId_type);
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

  @Override
  public DataType getTypeID(final int id) {
    final String _sql = "SELECT * FROM type WHERE id_type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfNamaType = CursorUtil.getColumnIndexOrThrow(_cursor, "nama_type");
      final int _cursorIndexOfIdType = CursorUtil.getColumnIndexOrThrow(_cursor, "id_type");
      final DataType _result;
      if(_cursor.moveToFirst()) {
        final String _tmpNama_type;
        _tmpNama_type = _cursor.getString(_cursorIndexOfNamaType);
        final Integer _tmpId_type;
        if (_cursor.isNull(_cursorIndexOfIdType)) {
          _tmpId_type = null;
        } else {
          _tmpId_type = _cursor.getInt(_cursorIndexOfIdType);
        }
        _result = new DataType(_tmpNama_type,_tmpId_type);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
