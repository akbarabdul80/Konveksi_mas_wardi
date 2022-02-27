package com.zero.konveksiku.data.db.dao;

import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.zero.konveksiku.data.model.user.DataRekap;
import com.zero.konveksiku.data.model.user.DataRekapResult;
import com.zero.konveksiku.data.model.user.DataRekapResultMany;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RekapDao_Impl implements RekapDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DataRekap> __insertionAdapterOfDataRekap;

  private final EntityInsertionAdapter<DataRekapResult> __insertionAdapterOfDataRekapResult;

  public RekapDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDataRekap = new EntityInsertionAdapter<DataRekap>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `rekap` (`start_date`,`end_date`,`qty`,`id_rekap`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataRekap value) {
        if (value.getStart_date() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getStart_date());
        }
        if (value.getEnd_date() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getEnd_date());
        }
        stmt.bindLong(3, value.getQty());
        if (value.getId_rekap() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getId_rekap());
        }
      }
    };
    this.__insertionAdapterOfDataRekapResult = new EntityInsertionAdapter<DataRekapResult>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `rekap_result` (`rekap_id`,`result_id`,`id_rekap_result`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataRekapResult value) {
        stmt.bindLong(1, value.getRekap_id());
        stmt.bindLong(2, value.getResult_id());
        if (value.getId_rekap_result() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getId_rekap_result());
        }
      }
    };
  }

  @Override
  public long addRekap(final DataRekap data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfDataRekap.insertAndReturnId(data);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void addRekapResult(final List<DataRekapResult> data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDataRekapResult.insert(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<DataRekap>> getRekap() {
    final String _sql = "SELECT * FROM rekap ORDER BY start_date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"rekap"}, true, new Callable<List<DataRekap>>() {
      @Override
      public List<DataRekap> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
            final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
            final int _cursorIndexOfQty = CursorUtil.getColumnIndexOrThrow(_cursor, "qty");
            final int _cursorIndexOfIdRekap = CursorUtil.getColumnIndexOrThrow(_cursor, "id_rekap");
            final List<DataRekap> _result = new ArrayList<DataRekap>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final DataRekap _item;
              final String _tmpStart_date;
              _tmpStart_date = _cursor.getString(_cursorIndexOfStartDate);
              final String _tmpEnd_date;
              _tmpEnd_date = _cursor.getString(_cursorIndexOfEndDate);
              final int _tmpQty;
              _tmpQty = _cursor.getInt(_cursorIndexOfQty);
              final Integer _tmpId_rekap;
              if (_cursor.isNull(_cursorIndexOfIdRekap)) {
                _tmpId_rekap = null;
              } else {
                _tmpId_rekap = _cursor.getInt(_cursorIndexOfIdRekap);
              }
              _item = new DataRekap(_tmpStart_date,_tmpEnd_date,_tmpQty,_tmpId_rekap);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<DataRekapResultMany>> getRekapResult() {
    final String _sql = "SELECT * FROM rekap ORDER BY start_date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"rekap_result","rekap"}, true, new Callable<List<DataRekapResultMany>>() {
      @Override
      public List<DataRekapResultMany> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
            final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
            final int _cursorIndexOfQty = CursorUtil.getColumnIndexOrThrow(_cursor, "qty");
            final int _cursorIndexOfIdRekap = CursorUtil.getColumnIndexOrThrow(_cursor, "id_rekap");
            final LongSparseArray<ArrayList<DataRekapResult>> _collectionClient = new LongSparseArray<ArrayList<DataRekapResult>>();
            while (_cursor.moveToNext()) {
              if (!_cursor.isNull(_cursorIndexOfIdRekap)) {
                final long _tmpKey = _cursor.getLong(_cursorIndexOfIdRekap);
                ArrayList<DataRekapResult> _tmpClientCollection = _collectionClient.get(_tmpKey);
                if (_tmpClientCollection == null) {
                  _tmpClientCollection = new ArrayList<DataRekapResult>();
                  _collectionClient.put(_tmpKey, _tmpClientCollection);
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshiprekapResultAscomZeroKonveksikuDataModelUserDataRekapResult(_collectionClient);
            final List<DataRekapResultMany> _result = new ArrayList<DataRekapResultMany>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final DataRekapResultMany _item;
              final DataRekap _tmpResult;
              if (! (_cursor.isNull(_cursorIndexOfStartDate) && _cursor.isNull(_cursorIndexOfEndDate) && _cursor.isNull(_cursorIndexOfQty) && _cursor.isNull(_cursorIndexOfIdRekap))) {
                final String _tmpStart_date;
                _tmpStart_date = _cursor.getString(_cursorIndexOfStartDate);
                final String _tmpEnd_date;
                _tmpEnd_date = _cursor.getString(_cursorIndexOfEndDate);
                final int _tmpQty;
                _tmpQty = _cursor.getInt(_cursorIndexOfQty);
                final Integer _tmpId_rekap;
                if (_cursor.isNull(_cursorIndexOfIdRekap)) {
                  _tmpId_rekap = null;
                } else {
                  _tmpId_rekap = _cursor.getInt(_cursorIndexOfIdRekap);
                }
                _tmpResult = new DataRekap(_tmpStart_date,_tmpEnd_date,_tmpQty,_tmpId_rekap);
              }  else  {
                _tmpResult = null;
              }
              ArrayList<DataRekapResult> _tmpClientCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfIdRekap)) {
                final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfIdRekap);
                _tmpClientCollection_1 = _collectionClient.get(_tmpKey_1);
              }
              if (_tmpClientCollection_1 == null) {
                _tmpClientCollection_1 = new ArrayList<DataRekapResult>();
              }
              _item = new DataRekapResultMany(_tmpResult,_tmpClientCollection_1);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<DataRekapResultMany> getRekapResultByID(final int id_rekap) {
    final String _sql = "SELECT * FROM rekap WHERE id_rekap = ? ORDER BY start_date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id_rekap);
    return __db.getInvalidationTracker().createLiveData(new String[]{"rekap_result","rekap"}, true, new Callable<DataRekapResultMany>() {
      @Override
      public DataRekapResultMany call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
            final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
            final int _cursorIndexOfQty = CursorUtil.getColumnIndexOrThrow(_cursor, "qty");
            final int _cursorIndexOfIdRekap = CursorUtil.getColumnIndexOrThrow(_cursor, "id_rekap");
            final LongSparseArray<ArrayList<DataRekapResult>> _collectionClient = new LongSparseArray<ArrayList<DataRekapResult>>();
            while (_cursor.moveToNext()) {
              if (!_cursor.isNull(_cursorIndexOfIdRekap)) {
                final long _tmpKey = _cursor.getLong(_cursorIndexOfIdRekap);
                ArrayList<DataRekapResult> _tmpClientCollection = _collectionClient.get(_tmpKey);
                if (_tmpClientCollection == null) {
                  _tmpClientCollection = new ArrayList<DataRekapResult>();
                  _collectionClient.put(_tmpKey, _tmpClientCollection);
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshiprekapResultAscomZeroKonveksikuDataModelUserDataRekapResult(_collectionClient);
            final DataRekapResultMany _result;
            if(_cursor.moveToFirst()) {
              final DataRekap _tmpResult;
              if (! (_cursor.isNull(_cursorIndexOfStartDate) && _cursor.isNull(_cursorIndexOfEndDate) && _cursor.isNull(_cursorIndexOfQty) && _cursor.isNull(_cursorIndexOfIdRekap))) {
                final String _tmpStart_date;
                _tmpStart_date = _cursor.getString(_cursorIndexOfStartDate);
                final String _tmpEnd_date;
                _tmpEnd_date = _cursor.getString(_cursorIndexOfEndDate);
                final int _tmpQty;
                _tmpQty = _cursor.getInt(_cursorIndexOfQty);
                final Integer _tmpId_rekap;
                if (_cursor.isNull(_cursorIndexOfIdRekap)) {
                  _tmpId_rekap = null;
                } else {
                  _tmpId_rekap = _cursor.getInt(_cursorIndexOfIdRekap);
                }
                _tmpResult = new DataRekap(_tmpStart_date,_tmpEnd_date,_tmpQty,_tmpId_rekap);
              }  else  {
                _tmpResult = null;
              }
              ArrayList<DataRekapResult> _tmpClientCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfIdRekap)) {
                final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfIdRekap);
                _tmpClientCollection_1 = _collectionClient.get(_tmpKey_1);
              }
              if (_tmpClientCollection_1 == null) {
                _tmpClientCollection_1 = new ArrayList<DataRekapResult>();
              }
              _result = new DataRekapResultMany(_tmpResult,_tmpClientCollection_1);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  private void __fetchRelationshiprekapResultAscomZeroKonveksikuDataModelUserDataRekapResult(final LongSparseArray<ArrayList<DataRekapResult>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<DataRekapResult>> _tmpInnerMap = new LongSparseArray<ArrayList<DataRekapResult>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshiprekapResultAscomZeroKonveksikuDataModelUserDataRekapResult(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<DataRekapResult>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshiprekapResultAscomZeroKonveksikuDataModelUserDataRekapResult(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `rekap_id`,`result_id`,`id_rekap_result` FROM `rekap_result` WHERE `rekap_id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "rekap_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfRekapId = CursorUtil.getColumnIndex(_cursor, "rekap_id");
      final int _cursorIndexOfResultId = CursorUtil.getColumnIndex(_cursor, "result_id");
      final int _cursorIndexOfIdRekapResult = CursorUtil.getColumnIndex(_cursor, "id_rekap_result");
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final long _tmpKey = _cursor.getLong(_itemKeyIndex);
          ArrayList<DataRekapResult> _tmpRelation = _map.get(_tmpKey);
          if (_tmpRelation != null) {
            final DataRekapResult _item_1;
            final int _tmpRekap_id;
            if (_cursorIndexOfRekapId == -1) {
              _tmpRekap_id = 0;
            } else {
              _tmpRekap_id = _cursor.getInt(_cursorIndexOfRekapId);
            }
            final int _tmpResult_id;
            if (_cursorIndexOfResultId == -1) {
              _tmpResult_id = 0;
            } else {
              _tmpResult_id = _cursor.getInt(_cursorIndexOfResultId);
            }
            final Integer _tmpId_rekap_result;
            if (_cursorIndexOfIdRekapResult == -1) {
              _tmpId_rekap_result = null;
            } else {
              if (_cursor.isNull(_cursorIndexOfIdRekapResult)) {
                _tmpId_rekap_result = null;
              } else {
                _tmpId_rekap_result = _cursor.getInt(_cursorIndexOfIdRekapResult);
              }
            }
            _item_1 = new DataRekapResult(_tmpRekap_id,_tmpResult_id,_tmpId_rekap_result);
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
