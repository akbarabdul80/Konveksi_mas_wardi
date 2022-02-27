package com.zero.konveksiku.data.db.dao;

import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.zero.konveksiku.data.model.user.DataClient;
import com.zero.konveksiku.data.model.user.DataQtyResult;
import com.zero.konveksiku.data.model.user.DataResult;
import com.zero.konveksiku.data.model.user.DataResultClient;
import com.zero.konveksiku.data.model.user.DataResultClientUserType;
import com.zero.konveksiku.data.model.user.DataType;
import com.zero.konveksiku.data.model.user.DataUser;
import java.lang.Boolean;
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
public final class ResultDao_Impl implements ResultDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DataResult> __insertionAdapterOfDataResult;

  private final SharedSQLiteStatement __preparedStmtOfUpdateRekap;

  public ResultDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDataResult = new EntityInsertionAdapter<DataResult>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `result` (`client_id`,`user_id`,`type_id`,`date`,`qty`,`status`,`id_result`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataResult value) {
        stmt.bindLong(1, value.getClient_id());
        stmt.bindLong(2, value.getUser_id());
        stmt.bindLong(3, value.getType_id());
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDate());
        }
        stmt.bindLong(5, value.getQty());
        final Integer _tmp;
        _tmp = value.getStatus() == null ? null : (value.getStatus() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, _tmp);
        }
        if (value.getId_result() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getId_result());
        }
      }
    };
    this.__preparedStmtOfUpdateRekap = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE result SET status = 1 WHERE status = 0";
        return _query;
      }
    };
  }

  @Override
  public void addResult(final DataResult data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDataResult.insert(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void addRekap(final List<DataResult> data) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDataResult.insert(data);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateRekap() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateRekap.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateRekap.release(_stmt);
    }
  }

  @Override
  public LiveData<List<DataResultClientUserType>> getResultRekapUser() {
    final String _sql = "SELECT * FROM result WHERE status = 0 ORDER BY user_id, client_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"client","user","type","result"}, true, new Callable<List<DataResultClientUserType>>() {
      @Override
      public List<DataResultClientUserType> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfClientId = CursorUtil.getColumnIndexOrThrow(_cursor, "client_id");
            final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
            final int _cursorIndexOfTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "type_id");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
            final int _cursorIndexOfQty = CursorUtil.getColumnIndexOrThrow(_cursor, "qty");
            final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
            final int _cursorIndexOfIdResult = CursorUtil.getColumnIndexOrThrow(_cursor, "id_result");
            final LongSparseArray<DataClient> _collectionClient = new LongSparseArray<DataClient>();
            final LongSparseArray<DataUser> _collectionUser = new LongSparseArray<DataUser>();
            final LongSparseArray<DataType> _collectionType = new LongSparseArray<DataType>();
            while (_cursor.moveToNext()) {
              final long _tmpKey = _cursor.getLong(_cursorIndexOfClientId);
              _collectionClient.put(_tmpKey, null);
              final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfUserId);
              _collectionUser.put(_tmpKey_1, null);
              final long _tmpKey_2 = _cursor.getLong(_cursorIndexOfTypeId);
              _collectionType.put(_tmpKey_2, null);
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipclientAscomZeroMyapplicationDataModelUserDataClient(_collectionClient);
            __fetchRelationshipuserAscomZeroMyapplicationDataModelUserDataUser(_collectionUser);
            __fetchRelationshiptypeAscomZeroMyapplicationDataModelUserDataType(_collectionType);
            final List<DataResultClientUserType> _result = new ArrayList<DataResultClientUserType>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final DataResultClientUserType _item;
              final DataResult _tmpResult;
              if (! (_cursor.isNull(_cursorIndexOfClientId) && _cursor.isNull(_cursorIndexOfUserId) && _cursor.isNull(_cursorIndexOfTypeId) && _cursor.isNull(_cursorIndexOfDate) && _cursor.isNull(_cursorIndexOfQty) && _cursor.isNull(_cursorIndexOfStatus) && _cursor.isNull(_cursorIndexOfIdResult))) {
                final int _tmpClient_id;
                _tmpClient_id = _cursor.getInt(_cursorIndexOfClientId);
                final int _tmpUser_id;
                _tmpUser_id = _cursor.getInt(_cursorIndexOfUserId);
                final int _tmpType_id;
                _tmpType_id = _cursor.getInt(_cursorIndexOfTypeId);
                final String _tmpDate;
                _tmpDate = _cursor.getString(_cursorIndexOfDate);
                final int _tmpQty;
                _tmpQty = _cursor.getInt(_cursorIndexOfQty);
                final Boolean _tmpStatus;
                final Integer _tmp;
                if (_cursor.isNull(_cursorIndexOfStatus)) {
                  _tmp = null;
                } else {
                  _tmp = _cursor.getInt(_cursorIndexOfStatus);
                }
                _tmpStatus = _tmp == null ? null : _tmp != 0;
                _tmpResult = new DataResult(_tmpClient_id,_tmpUser_id,_tmpType_id,_tmpDate,_tmpQty,_tmpStatus);
                final Integer _tmpId_result;
                if (_cursor.isNull(_cursorIndexOfIdResult)) {
                  _tmpId_result = null;
                } else {
                  _tmpId_result = _cursor.getInt(_cursorIndexOfIdResult);
                }
                _tmpResult.setId_result(_tmpId_result);
              }  else  {
                _tmpResult = null;
              }
              DataClient _tmpClient = null;
              final long _tmpKey_3 = _cursor.getLong(_cursorIndexOfClientId);
              _tmpClient = _collectionClient.get(_tmpKey_3);
              DataUser _tmpUser = null;
              final long _tmpKey_4 = _cursor.getLong(_cursorIndexOfUserId);
              _tmpUser = _collectionUser.get(_tmpKey_4);
              DataType _tmpType = null;
              final long _tmpKey_5 = _cursor.getLong(_cursorIndexOfTypeId);
              _tmpType = _collectionType.get(_tmpKey_5);
              _item = new DataResultClientUserType(_tmpResult,_tmpClient,_tmpUser,_tmpType);
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
  public LiveData<List<DataResultClientUserType>> getResultRekapUserByID(final int id_result) {
    final String _sql = "SELECT * FROM result WHERE id_result = ? ORDER BY user_id, client_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id_result);
    return __db.getInvalidationTracker().createLiveData(new String[]{"client","user","type","result"}, true, new Callable<List<DataResultClientUserType>>() {
      @Override
      public List<DataResultClientUserType> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfClientId = CursorUtil.getColumnIndexOrThrow(_cursor, "client_id");
            final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
            final int _cursorIndexOfTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "type_id");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
            final int _cursorIndexOfQty = CursorUtil.getColumnIndexOrThrow(_cursor, "qty");
            final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
            final int _cursorIndexOfIdResult = CursorUtil.getColumnIndexOrThrow(_cursor, "id_result");
            final LongSparseArray<DataClient> _collectionClient = new LongSparseArray<DataClient>();
            final LongSparseArray<DataUser> _collectionUser = new LongSparseArray<DataUser>();
            final LongSparseArray<DataType> _collectionType = new LongSparseArray<DataType>();
            while (_cursor.moveToNext()) {
              final long _tmpKey = _cursor.getLong(_cursorIndexOfClientId);
              _collectionClient.put(_tmpKey, null);
              final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfUserId);
              _collectionUser.put(_tmpKey_1, null);
              final long _tmpKey_2 = _cursor.getLong(_cursorIndexOfTypeId);
              _collectionType.put(_tmpKey_2, null);
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipclientAscomZeroMyapplicationDataModelUserDataClient(_collectionClient);
            __fetchRelationshipuserAscomZeroMyapplicationDataModelUserDataUser(_collectionUser);
            __fetchRelationshiptypeAscomZeroMyapplicationDataModelUserDataType(_collectionType);
            final List<DataResultClientUserType> _result = new ArrayList<DataResultClientUserType>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final DataResultClientUserType _item;
              final DataResult _tmpResult;
              if (! (_cursor.isNull(_cursorIndexOfClientId) && _cursor.isNull(_cursorIndexOfUserId) && _cursor.isNull(_cursorIndexOfTypeId) && _cursor.isNull(_cursorIndexOfDate) && _cursor.isNull(_cursorIndexOfQty) && _cursor.isNull(_cursorIndexOfStatus) && _cursor.isNull(_cursorIndexOfIdResult))) {
                final int _tmpClient_id;
                _tmpClient_id = _cursor.getInt(_cursorIndexOfClientId);
                final int _tmpUser_id;
                _tmpUser_id = _cursor.getInt(_cursorIndexOfUserId);
                final int _tmpType_id;
                _tmpType_id = _cursor.getInt(_cursorIndexOfTypeId);
                final String _tmpDate;
                _tmpDate = _cursor.getString(_cursorIndexOfDate);
                final int _tmpQty;
                _tmpQty = _cursor.getInt(_cursorIndexOfQty);
                final Boolean _tmpStatus;
                final Integer _tmp;
                if (_cursor.isNull(_cursorIndexOfStatus)) {
                  _tmp = null;
                } else {
                  _tmp = _cursor.getInt(_cursorIndexOfStatus);
                }
                _tmpStatus = _tmp == null ? null : _tmp != 0;
                _tmpResult = new DataResult(_tmpClient_id,_tmpUser_id,_tmpType_id,_tmpDate,_tmpQty,_tmpStatus);
                final Integer _tmpId_result;
                if (_cursor.isNull(_cursorIndexOfIdResult)) {
                  _tmpId_result = null;
                } else {
                  _tmpId_result = _cursor.getInt(_cursorIndexOfIdResult);
                }
                _tmpResult.setId_result(_tmpId_result);
              }  else  {
                _tmpResult = null;
              }
              DataClient _tmpClient = null;
              final long _tmpKey_3 = _cursor.getLong(_cursorIndexOfClientId);
              _tmpClient = _collectionClient.get(_tmpKey_3);
              DataUser _tmpUser = null;
              final long _tmpKey_4 = _cursor.getLong(_cursorIndexOfUserId);
              _tmpUser = _collectionUser.get(_tmpKey_4);
              DataType _tmpType = null;
              final long _tmpKey_5 = _cursor.getLong(_cursorIndexOfTypeId);
              _tmpType = _collectionType.get(_tmpKey_5);
              _item = new DataResultClientUserType(_tmpResult,_tmpClient,_tmpUser,_tmpType);
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
  public LiveData<List<DataResultClient>> getResultRekapClient() {
    final String _sql = "SELECT nama_client, client_id, SUM(qty) as all_qty, nama_type  FROM result JOIN client ON client_id = id_client JOIN type ON type_id = id_type WHERE status = 0 GROUP BY client_id, type_id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"result","client","type"}, true, new Callable<List<DataResultClient>>() {
      @Override
      public List<DataResultClient> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfNamaClient = CursorUtil.getColumnIndexOrThrow(_cursor, "nama_client");
            final int _cursorIndexOfClientId = CursorUtil.getColumnIndexOrThrow(_cursor, "client_id");
            final int _cursorIndexOfAllQty = CursorUtil.getColumnIndexOrThrow(_cursor, "all_qty");
            final int _cursorIndexOfNamaType = CursorUtil.getColumnIndexOrThrow(_cursor, "nama_type");
            final List<DataResultClient> _result = new ArrayList<DataResultClient>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final DataResultClient _item;
              final String _tmpNama_client;
              _tmpNama_client = _cursor.getString(_cursorIndexOfNamaClient);
              final String _tmpClient_id;
              _tmpClient_id = _cursor.getString(_cursorIndexOfClientId);
              final int _tmpAll_qty;
              _tmpAll_qty = _cursor.getInt(_cursorIndexOfAllQty);
              final String _tmpNama_type;
              _tmpNama_type = _cursor.getString(_cursorIndexOfNamaType);
              _item = new DataResultClient(_tmpNama_client,_tmpClient_id,_tmpAll_qty,_tmpNama_type,null);
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
  public LiveData<List<DataResultClient>> getResultRekapClientByID(final int id_result) {
    final String _sql = "SELECT nama_client, client_id, SUM(qty) as all_qty, nama_type  FROM result JOIN client ON client_id = id_client JOIN type ON type_id = id_type WHERE id_result = ? GROUP BY client_id, type_id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id_result);
    return __db.getInvalidationTracker().createLiveData(new String[]{"result","client","type"}, true, new Callable<List<DataResultClient>>() {
      @Override
      public List<DataResultClient> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfNamaClient = CursorUtil.getColumnIndexOrThrow(_cursor, "nama_client");
            final int _cursorIndexOfClientId = CursorUtil.getColumnIndexOrThrow(_cursor, "client_id");
            final int _cursorIndexOfAllQty = CursorUtil.getColumnIndexOrThrow(_cursor, "all_qty");
            final int _cursorIndexOfNamaType = CursorUtil.getColumnIndexOrThrow(_cursor, "nama_type");
            final List<DataResultClient> _result = new ArrayList<DataResultClient>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final DataResultClient _item;
              final String _tmpNama_client;
              _tmpNama_client = _cursor.getString(_cursorIndexOfNamaClient);
              final String _tmpClient_id;
              _tmpClient_id = _cursor.getString(_cursorIndexOfClientId);
              final int _tmpAll_qty;
              _tmpAll_qty = _cursor.getInt(_cursorIndexOfAllQty);
              final String _tmpNama_type;
              _tmpNama_type = _cursor.getString(_cursorIndexOfNamaType);
              _item = new DataResultClient(_tmpNama_client,_tmpClient_id,_tmpAll_qty,_tmpNama_type,null);
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
  public LiveData<List<DataResult>> getAllNotRekap() {
    final String _sql = "SELECT * FROM result WHERE status = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"result"}, false, new Callable<List<DataResult>>() {
      @Override
      public List<DataResult> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfClientId = CursorUtil.getColumnIndexOrThrow(_cursor, "client_id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "type_id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfQty = CursorUtil.getColumnIndexOrThrow(_cursor, "qty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfIdResult = CursorUtil.getColumnIndexOrThrow(_cursor, "id_result");
          final List<DataResult> _result = new ArrayList<DataResult>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DataResult _item;
            final int _tmpClient_id;
            _tmpClient_id = _cursor.getInt(_cursorIndexOfClientId);
            final int _tmpUser_id;
            _tmpUser_id = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpType_id;
            _tmpType_id = _cursor.getInt(_cursorIndexOfTypeId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final int _tmpQty;
            _tmpQty = _cursor.getInt(_cursorIndexOfQty);
            final Boolean _tmpStatus;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfStatus);
            }
            _tmpStatus = _tmp == null ? null : _tmp != 0;
            _item = new DataResult(_tmpClient_id,_tmpUser_id,_tmpType_id,_tmpDate,_tmpQty,_tmpStatus);
            final Integer _tmpId_result;
            if (_cursor.isNull(_cursorIndexOfIdResult)) {
              _tmpId_result = null;
            } else {
              _tmpId_result = _cursor.getInt(_cursorIndexOfIdResult);
            }
            _item.setId_result(_tmpId_result);
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
  public LiveData<DataQtyResult> getAllQty() {
    final String _sql = "SELECT SUM(qty) as all_qty FROM result WHERE status = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"result"}, false, new Callable<DataQtyResult>() {
      @Override
      public DataQtyResult call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAllQty = CursorUtil.getColumnIndexOrThrow(_cursor, "all_qty");
          final DataQtyResult _result;
          if(_cursor.moveToFirst()) {
            final int _tmpAll_qty;
            _tmpAll_qty = _cursor.getInt(_cursorIndexOfAllQty);
            _result = new DataQtyResult(_tmpAll_qty);
          } else {
            _result = null;
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
  public LiveData<List<DataResultClientUserType>> getResultToday(final String date) {
    final String _sql = "SELECT * FROM result WHERE date LIKE '%' ||? || '%'  ORDER BY user_id, client_id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"client","user","type","result"}, true, new Callable<List<DataResultClientUserType>>() {
      @Override
      public List<DataResultClientUserType> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfClientId = CursorUtil.getColumnIndexOrThrow(_cursor, "client_id");
            final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
            final int _cursorIndexOfTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "type_id");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
            final int _cursorIndexOfQty = CursorUtil.getColumnIndexOrThrow(_cursor, "qty");
            final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
            final int _cursorIndexOfIdResult = CursorUtil.getColumnIndexOrThrow(_cursor, "id_result");
            final LongSparseArray<DataClient> _collectionClient = new LongSparseArray<DataClient>();
            final LongSparseArray<DataUser> _collectionUser = new LongSparseArray<DataUser>();
            final LongSparseArray<DataType> _collectionType = new LongSparseArray<DataType>();
            while (_cursor.moveToNext()) {
              final long _tmpKey = _cursor.getLong(_cursorIndexOfClientId);
              _collectionClient.put(_tmpKey, null);
              final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfUserId);
              _collectionUser.put(_tmpKey_1, null);
              final long _tmpKey_2 = _cursor.getLong(_cursorIndexOfTypeId);
              _collectionType.put(_tmpKey_2, null);
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipclientAscomZeroMyapplicationDataModelUserDataClient(_collectionClient);
            __fetchRelationshipuserAscomZeroMyapplicationDataModelUserDataUser(_collectionUser);
            __fetchRelationshiptypeAscomZeroMyapplicationDataModelUserDataType(_collectionType);
            final List<DataResultClientUserType> _result = new ArrayList<DataResultClientUserType>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final DataResultClientUserType _item;
              final DataResult _tmpResult;
              if (! (_cursor.isNull(_cursorIndexOfClientId) && _cursor.isNull(_cursorIndexOfUserId) && _cursor.isNull(_cursorIndexOfTypeId) && _cursor.isNull(_cursorIndexOfDate) && _cursor.isNull(_cursorIndexOfQty) && _cursor.isNull(_cursorIndexOfStatus) && _cursor.isNull(_cursorIndexOfIdResult))) {
                final int _tmpClient_id;
                _tmpClient_id = _cursor.getInt(_cursorIndexOfClientId);
                final int _tmpUser_id;
                _tmpUser_id = _cursor.getInt(_cursorIndexOfUserId);
                final int _tmpType_id;
                _tmpType_id = _cursor.getInt(_cursorIndexOfTypeId);
                final String _tmpDate;
                _tmpDate = _cursor.getString(_cursorIndexOfDate);
                final int _tmpQty;
                _tmpQty = _cursor.getInt(_cursorIndexOfQty);
                final Boolean _tmpStatus;
                final Integer _tmp;
                if (_cursor.isNull(_cursorIndexOfStatus)) {
                  _tmp = null;
                } else {
                  _tmp = _cursor.getInt(_cursorIndexOfStatus);
                }
                _tmpStatus = _tmp == null ? null : _tmp != 0;
                _tmpResult = new DataResult(_tmpClient_id,_tmpUser_id,_tmpType_id,_tmpDate,_tmpQty,_tmpStatus);
                final Integer _tmpId_result;
                if (_cursor.isNull(_cursorIndexOfIdResult)) {
                  _tmpId_result = null;
                } else {
                  _tmpId_result = _cursor.getInt(_cursorIndexOfIdResult);
                }
                _tmpResult.setId_result(_tmpId_result);
              }  else  {
                _tmpResult = null;
              }
              DataClient _tmpClient = null;
              final long _tmpKey_3 = _cursor.getLong(_cursorIndexOfClientId);
              _tmpClient = _collectionClient.get(_tmpKey_3);
              DataUser _tmpUser = null;
              final long _tmpKey_4 = _cursor.getLong(_cursorIndexOfUserId);
              _tmpUser = _collectionUser.get(_tmpKey_4);
              DataType _tmpType = null;
              final long _tmpKey_5 = _cursor.getLong(_cursorIndexOfTypeId);
              _tmpType = _collectionType.get(_tmpKey_5);
              _item = new DataResultClientUserType(_tmpResult,_tmpClient,_tmpUser,_tmpType);
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

  private void __fetchRelationshipclientAscomZeroMyapplicationDataModelUserDataClient(final LongSparseArray<DataClient> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<DataClient> _tmpInnerMap = new LongSparseArray<DataClient>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), null);
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipclientAscomZeroMyapplicationDataModelUserDataClient(_tmpInnerMap);
          _map.putAll(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<DataClient>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipclientAscomZeroMyapplicationDataModelUserDataClient(_tmpInnerMap);
        _map.putAll(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `nama_client`,`id_client` FROM `client` WHERE `id_client` IN (");
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
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id_client");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfNamaClient = CursorUtil.getColumnIndex(_cursor, "nama_client");
      final int _cursorIndexOfIdClient = CursorUtil.getColumnIndex(_cursor, "id_client");
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final DataClient _item_1;
          final String _tmpNama_client;
          if (_cursorIndexOfNamaClient == -1) {
            _tmpNama_client = null;
          } else {
            _tmpNama_client = _cursor.getString(_cursorIndexOfNamaClient);
          }
          final Integer _tmpId_client;
          if (_cursorIndexOfIdClient == -1) {
            _tmpId_client = null;
          } else {
            if (_cursor.isNull(_cursorIndexOfIdClient)) {
              _tmpId_client = null;
            } else {
              _tmpId_client = _cursor.getInt(_cursorIndexOfIdClient);
            }
          }
          _item_1 = new DataClient(_tmpNama_client,_tmpId_client);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipuserAscomZeroMyapplicationDataModelUserDataUser(final LongSparseArray<DataUser> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<DataUser> _tmpInnerMap = new LongSparseArray<DataUser>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), null);
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipuserAscomZeroMyapplicationDataModelUserDataUser(_tmpInnerMap);
          _map.putAll(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<DataUser>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipuserAscomZeroMyapplicationDataModelUserDataUser(_tmpInnerMap);
        _map.putAll(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `name_user`,`id_user` FROM `user` WHERE `id_user` IN (");
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
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id_user");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfNamaUser = CursorUtil.getColumnIndex(_cursor, "name_user");
      final int _cursorIndexOfIdUser = CursorUtil.getColumnIndex(_cursor, "id_user");
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final DataUser _item_1;
          final String _tmpNama_user;
          if (_cursorIndexOfNamaUser == -1) {
            _tmpNama_user = null;
          } else {
            _tmpNama_user = _cursor.getString(_cursorIndexOfNamaUser);
          }
          final Integer _tmpId_user;
          if (_cursorIndexOfIdUser == -1) {
            _tmpId_user = null;
          } else {
            if (_cursor.isNull(_cursorIndexOfIdUser)) {
              _tmpId_user = null;
            } else {
              _tmpId_user = _cursor.getInt(_cursorIndexOfIdUser);
            }
          }
          _item_1 = new DataUser(_tmpNama_user,_tmpId_user);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshiptypeAscomZeroMyapplicationDataModelUserDataType(final LongSparseArray<DataType> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<DataType> _tmpInnerMap = new LongSparseArray<DataType>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), null);
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshiptypeAscomZeroMyapplicationDataModelUserDataType(_tmpInnerMap);
          _map.putAll(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<DataType>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshiptypeAscomZeroMyapplicationDataModelUserDataType(_tmpInnerMap);
        _map.putAll(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `nama_type`,`id_type` FROM `type` WHERE `id_type` IN (");
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
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id_type");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfNamaType = CursorUtil.getColumnIndex(_cursor, "nama_type");
      final int _cursorIndexOfIdType = CursorUtil.getColumnIndex(_cursor, "id_type");
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final DataType _item_1;
          final String _tmpNama_type;
          if (_cursorIndexOfNamaType == -1) {
            _tmpNama_type = null;
          } else {
            _tmpNama_type = _cursor.getString(_cursorIndexOfNamaType);
          }
          final Integer _tmpId_type;
          if (_cursorIndexOfIdType == -1) {
            _tmpId_type = null;
          } else {
            if (_cursor.isNull(_cursorIndexOfIdType)) {
              _tmpId_type = null;
            } else {
              _tmpId_type = _cursor.getInt(_cursorIndexOfIdType);
            }
          }
          _item_1 = new DataType(_tmpNama_type,_tmpId_type);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
