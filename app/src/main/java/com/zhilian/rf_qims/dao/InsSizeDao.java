package com.zhilian.rf_qims.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhilian.rf_qims.entity.InsSize;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "INS_SIZE".
*/
public class InsSizeDao extends AbstractDao<InsSize, Long> {

    public static final String TABLENAME = "INS_SIZE";

    /**
     * Properties of entity InsSize.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Rid = new Property(1, long.class, "rid", false, "RID");
        public final static Property Pmd = new Property(2, String.class, "pmd", false, "PMD");
        public final static Property Zjgc = new Property(3, String.class, "zjgc", false, "ZJGC");
    }


    public InsSizeDao(DaoConfig config) {
        super(config);
    }
    
    public InsSizeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INS_SIZE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"RID\" INTEGER NOT NULL ," + // 1: rid
                "\"PMD\" TEXT," + // 2: pmd
                "\"ZJGC\" TEXT);"); // 3: zjgc
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INS_SIZE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, InsSize entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getRid());
 
        String pmd = entity.getPmd();
        if (pmd != null) {
            stmt.bindString(3, pmd);
        }
 
        String zjgc = entity.getZjgc();
        if (zjgc != null) {
            stmt.bindString(4, zjgc);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, InsSize entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getRid());
 
        String pmd = entity.getPmd();
        if (pmd != null) {
            stmt.bindString(3, pmd);
        }
 
        String zjgc = entity.getZjgc();
        if (zjgc != null) {
            stmt.bindString(4, zjgc);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public InsSize readEntity(Cursor cursor, int offset) {
        InsSize entity = new InsSize( //
            cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // rid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // pmd
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // zjgc
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, InsSize entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setRid(cursor.getLong(offset + 1));
        entity.setPmd(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setZjgc(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(InsSize entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(InsSize entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(InsSize entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
