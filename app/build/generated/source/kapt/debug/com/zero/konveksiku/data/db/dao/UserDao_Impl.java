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
import com.zero.konveksiku.data.model.user.DataUser;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DataUser> __insertionAdapterOfDataUser;

  private final EntityDeletionOrUpdateAdapter<DataUser> __deletionAdapterOfDataUser;

  private final EntityDeletionOrUpdateAdapter<DataUser> __updateAdapterOfDataUser;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDataUser = new EntityInsertionAdapter<DataUser>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `user` (`name_user`,`id_user`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataUser value) {
        if (value.getNama_user() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNama_user());
        }
        if (value.getId_user() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getId_user());
        }
      }
    };
    this.__deletionAdapterOfDataUser = new EntityDeletionOrUpdateAdapter<DataUser>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `user` WHERE `id_user` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataUser value) {
        if (value.getId_user() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId_user());
        }
      }
    };
    this.__updateAdapterOfDataUser = new EntityDeletionOrUpdateAdapter<DataUser>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `user` SET `name_user` = ?,`id_user` = ? WHERE `id_user` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataUser value) {
        if (value.getNama_user() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNama_user());
        }
        if (value.getId_user() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getId_user());
        }
        if (value.getId_user() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getId_user());
        }
      }
    };
  }

  @Override
  public void addUser(final DataUser data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDataUser.insert(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final DataUser data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDataUser.handle(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final DataUser dataUser) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDataUser.handle(dataUser);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<DataUser>> getUser() {
    final String _sql = "SELECT * FROM user ORDER BY name_user ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"user"}, false, new Callable<List<DataUser>>() {
      @Override
      public List<DataUser> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNamaUser = CursorUtil.getColumnIndexOrThrow(_cursor, "name_user");
          final int _cursorIndexOfIdUser = CursorUtil.getColumnIndexOrThrow(_cursor, "id_user");
          final List<DataUser> _result = new ArrayList<DataUser>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DataUser _item;
            final String _tmpNama_user;
            _tmpNama_user = _cursor.getString(_cursorIndexOfNamaUser);
            final Integer _tmpId_user;
            if (_cursor.isNull(_cursorIndexOfIdUser)) {
              _tmpId_user = null;
            } else {
              _tmpId_user = _cursor.getInt(_cursorIndexOfIdUser);
            }
            _item = new DataUser(_tmpNama_user,_tmpId_user);
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
