package com.zero.konveksiku.data.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.zero.konveksiku.data.db.dao.ClientDao;
import com.zero.konveksiku.data.db.dao.ClientDao_Impl;
import com.zero.konveksiku.data.db.dao.RekapDao;
import com.zero.konveksiku.data.db.dao.RekapDao_Impl;
import com.zero.konveksiku.data.db.dao.ResultDao;
import com.zero.konveksiku.data.db.dao.ResultDao_Impl;
import com.zero.konveksiku.data.db.dao.TypeDao;
import com.zero.konveksiku.data.db.dao.TypeDao_Impl;
import com.zero.konveksiku.data.db.dao.UserDao;
import com.zero.konveksiku.data.db.dao.UserDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RoomDB_Impl extends RoomDB {
  private volatile UserDao _userDao;

  private volatile ClientDao _clientDao;

  private volatile TypeDao _typeDao;

  private volatile ResultDao _resultDao;

  private volatile RekapDao _rekapDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user` (`name_user` TEXT NOT NULL, `id_user` INTEGER PRIMARY KEY AUTOINCREMENT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `client` (`nama_client` TEXT NOT NULL, `id_client` INTEGER PRIMARY KEY AUTOINCREMENT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `type` (`nama_type` TEXT NOT NULL, `id_type` INTEGER PRIMARY KEY AUTOINCREMENT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `result` (`client_id` INTEGER NOT NULL, `user_id` INTEGER NOT NULL, `type_id` INTEGER NOT NULL, `date` TEXT NOT NULL, `qty` INTEGER NOT NULL, `status` INTEGER, `id_result` INTEGER PRIMARY KEY AUTOINCREMENT, FOREIGN KEY(`client_id`) REFERENCES `client`(`id_client`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`user_id`) REFERENCES `user`(`id_user`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`type_id`) REFERENCES `type`(`id_type`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_result_client_id` ON `result` (`client_id`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_result_user_id` ON `result` (`user_id`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_result_type_id` ON `result` (`type_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `rekap` (`start_date` TEXT NOT NULL, `end_date` TEXT NOT NULL, `qty` INTEGER NOT NULL, `id_rekap` INTEGER PRIMARY KEY AUTOINCREMENT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `rekap_result` (`rekap_id` INTEGER NOT NULL, `result_id` INTEGER NOT NULL, `id_rekap_result` INTEGER, PRIMARY KEY(`id_rekap_result`), FOREIGN KEY(`rekap_id`) REFERENCES `rekap`(`id_rekap`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`result_id`) REFERENCES `result`(`id_result`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_rekap_result_rekap_id` ON `rekap_result` (`rekap_id`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_rekap_result_result_id` ON `rekap_result` (`result_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'eb63f4dae69ad036e4dce9596da98826')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `user`");
        _db.execSQL("DROP TABLE IF EXISTS `client`");
        _db.execSQL("DROP TABLE IF EXISTS `type`");
        _db.execSQL("DROP TABLE IF EXISTS `result`");
        _db.execSQL("DROP TABLE IF EXISTS `rekap`");
        _db.execSQL("DROP TABLE IF EXISTS `rekap_result`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(2);
        _columnsUser.put("name_user", new TableInfo.Column("name_user", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("id_user", new TableInfo.Column("id_user", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("user", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "user");
        if (! _infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "user(com.zero.myapplication.data.model.user.DataUser).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsClient = new HashMap<String, TableInfo.Column>(2);
        _columnsClient.put("nama_client", new TableInfo.Column("nama_client", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClient.put("id_client", new TableInfo.Column("id_client", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysClient = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesClient = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoClient = new TableInfo("client", _columnsClient, _foreignKeysClient, _indicesClient);
        final TableInfo _existingClient = TableInfo.read(_db, "client");
        if (! _infoClient.equals(_existingClient)) {
          return new RoomOpenHelper.ValidationResult(false, "client(com.zero.myapplication.data.model.user.DataClient).\n"
                  + " Expected:\n" + _infoClient + "\n"
                  + " Found:\n" + _existingClient);
        }
        final HashMap<String, TableInfo.Column> _columnsType = new HashMap<String, TableInfo.Column>(2);
        _columnsType.put("nama_type", new TableInfo.Column("nama_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsType.put("id_type", new TableInfo.Column("id_type", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysType = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesType = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoType = new TableInfo("type", _columnsType, _foreignKeysType, _indicesType);
        final TableInfo _existingType = TableInfo.read(_db, "type");
        if (! _infoType.equals(_existingType)) {
          return new RoomOpenHelper.ValidationResult(false, "type(com.zero.myapplication.data.model.user.DataType).\n"
                  + " Expected:\n" + _infoType + "\n"
                  + " Found:\n" + _existingType);
        }
        final HashMap<String, TableInfo.Column> _columnsResult = new HashMap<String, TableInfo.Column>(7);
        _columnsResult.put("client_id", new TableInfo.Column("client_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResult.put("user_id", new TableInfo.Column("user_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResult.put("type_id", new TableInfo.Column("type_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResult.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResult.put("qty", new TableInfo.Column("qty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResult.put("status", new TableInfo.Column("status", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsResult.put("id_result", new TableInfo.Column("id_result", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysResult = new HashSet<TableInfo.ForeignKey>(3);
        _foreignKeysResult.add(new TableInfo.ForeignKey("client", "CASCADE", "NO ACTION",Arrays.asList("client_id"), Arrays.asList("id_client")));
        _foreignKeysResult.add(new TableInfo.ForeignKey("user", "CASCADE", "NO ACTION",Arrays.asList("user_id"), Arrays.asList("id_user")));
        _foreignKeysResult.add(new TableInfo.ForeignKey("type", "CASCADE", "NO ACTION",Arrays.asList("type_id"), Arrays.asList("id_type")));
        final HashSet<TableInfo.Index> _indicesResult = new HashSet<TableInfo.Index>(3);
        _indicesResult.add(new TableInfo.Index("index_result_client_id", false, Arrays.asList("client_id")));
        _indicesResult.add(new TableInfo.Index("index_result_user_id", false, Arrays.asList("user_id")));
        _indicesResult.add(new TableInfo.Index("index_result_type_id", false, Arrays.asList("type_id")));
        final TableInfo _infoResult = new TableInfo("result", _columnsResult, _foreignKeysResult, _indicesResult);
        final TableInfo _existingResult = TableInfo.read(_db, "result");
        if (! _infoResult.equals(_existingResult)) {
          return new RoomOpenHelper.ValidationResult(false, "result(com.zero.myapplication.data.model.user.DataResult).\n"
                  + " Expected:\n" + _infoResult + "\n"
                  + " Found:\n" + _existingResult);
        }
        final HashMap<String, TableInfo.Column> _columnsRekap = new HashMap<String, TableInfo.Column>(4);
        _columnsRekap.put("start_date", new TableInfo.Column("start_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRekap.put("end_date", new TableInfo.Column("end_date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRekap.put("qty", new TableInfo.Column("qty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRekap.put("id_rekap", new TableInfo.Column("id_rekap", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRekap = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRekap = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRekap = new TableInfo("rekap", _columnsRekap, _foreignKeysRekap, _indicesRekap);
        final TableInfo _existingRekap = TableInfo.read(_db, "rekap");
        if (! _infoRekap.equals(_existingRekap)) {
          return new RoomOpenHelper.ValidationResult(false, "rekap(com.zero.myapplication.data.model.user.DataRekap).\n"
                  + " Expected:\n" + _infoRekap + "\n"
                  + " Found:\n" + _existingRekap);
        }
        final HashMap<String, TableInfo.Column> _columnsRekapResult = new HashMap<String, TableInfo.Column>(3);
        _columnsRekapResult.put("rekap_id", new TableInfo.Column("rekap_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRekapResult.put("result_id", new TableInfo.Column("result_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRekapResult.put("id_rekap_result", new TableInfo.Column("id_rekap_result", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRekapResult = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysRekapResult.add(new TableInfo.ForeignKey("rekap", "CASCADE", "NO ACTION",Arrays.asList("rekap_id"), Arrays.asList("id_rekap")));
        _foreignKeysRekapResult.add(new TableInfo.ForeignKey("result", "CASCADE", "NO ACTION",Arrays.asList("result_id"), Arrays.asList("id_result")));
        final HashSet<TableInfo.Index> _indicesRekapResult = new HashSet<TableInfo.Index>(2);
        _indicesRekapResult.add(new TableInfo.Index("index_rekap_result_rekap_id", false, Arrays.asList("rekap_id")));
        _indicesRekapResult.add(new TableInfo.Index("index_rekap_result_result_id", false, Arrays.asList("result_id")));
        final TableInfo _infoRekapResult = new TableInfo("rekap_result", _columnsRekapResult, _foreignKeysRekapResult, _indicesRekapResult);
        final TableInfo _existingRekapResult = TableInfo.read(_db, "rekap_result");
        if (! _infoRekapResult.equals(_existingRekapResult)) {
          return new RoomOpenHelper.ValidationResult(false, "rekap_result(com.zero.myapplication.data.model.user.DataRekapResult).\n"
                  + " Expected:\n" + _infoRekapResult + "\n"
                  + " Found:\n" + _existingRekapResult);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "eb63f4dae69ad036e4dce9596da98826", "ec3a4440dc627982f399a5c704e5d06d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user","client","type","result","rekap","rekap_result");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `user`");
      _db.execSQL("DELETE FROM `client`");
      _db.execSQL("DELETE FROM `type`");
      _db.execSQL("DELETE FROM `result`");
      _db.execSQL("DELETE FROM `rekap`");
      _db.execSQL("DELETE FROM `rekap_result`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public UserDao user() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public ClientDao client() {
    if (_clientDao != null) {
      return _clientDao;
    } else {
      synchronized(this) {
        if(_clientDao == null) {
          _clientDao = new ClientDao_Impl(this);
        }
        return _clientDao;
      }
    }
  }

  @Override
  public TypeDao type() {
    if (_typeDao != null) {
      return _typeDao;
    } else {
      synchronized(this) {
        if(_typeDao == null) {
          _typeDao = new TypeDao_Impl(this);
        }
        return _typeDao;
      }
    }
  }

  @Override
  public ResultDao result() {
    if (_resultDao != null) {
      return _resultDao;
    } else {
      synchronized(this) {
        if(_resultDao == null) {
          _resultDao = new ResultDao_Impl(this);
        }
        return _resultDao;
      }
    }
  }

  @Override
  public RekapDao rekap() {
    if (_rekapDao != null) {
      return _rekapDao;
    } else {
      synchronized(this) {
        if(_rekapDao == null) {
          _rekapDao = new RekapDao_Impl(this);
        }
        return _rekapDao;
      }
    }
  }
}
