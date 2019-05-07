package com.zhilian.rf_qims.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhilian.rf_qims.entity.EnterpriseCreditJson;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ENTERPRISE_CREDIT_JSON".
*/
public class EnterpriseCreditJsonDao extends AbstractDao<EnterpriseCreditJson, Long> {

    public static final String TABLENAME = "ENTERPRISE_CREDIT_JSON";

    /**
     * Properties of entity EnterpriseCreditJson.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Oid = new Property(1, Integer.class, "oid", false, "OID");
        public final static Property Eid = new Property(2, Integer.class, "eid", false, "EID");
        public final static Property Unit_name = new Property(3, String.class, "unit_name", false, "UNIT_NAME");
        public final static Property Submit_date = new Property(4, java.util.Date.class, "submit_date", false, "SUBMIT_DATE");
        public final static Property Status = new Property(5, Integer.class, "status", false, "STATUS");
        public final static Property Type = new Property(6, Integer.class, "type", false, "TYPE");
        public final static Property Cid = new Property(7, Integer.class, "cid", false, "CID");
        public final static Property Ctstatus = new Property(8, Integer.class, "ctstatus", false, "CTSTATUS");
        public final static Property Qrank = new Property(9, String.class, "qrank", false, "QRANK");
        public final static Property Total = new Property(10, float.class, "total", false, "TOTAL");
        public final static Property Annual = new Property(11, String.class, "annual", false, "ANNUAL");
    }


    public EnterpriseCreditJsonDao(DaoConfig config) {
        super(config);
    }
    
    public EnterpriseCreditJsonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ENTERPRISE_CREDIT_JSON\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"OID\" INTEGER," + // 1: oid
                "\"EID\" INTEGER," + // 2: eid
                "\"UNIT_NAME\" TEXT," + // 3: unit_name
                "\"SUBMIT_DATE\" INTEGER," + // 4: submit_date
                "\"STATUS\" INTEGER," + // 5: status
                "\"TYPE\" INTEGER," + // 6: type
                "\"CID\" INTEGER," + // 7: cid
                "\"CTSTATUS\" INTEGER," + // 8: ctstatus
                "\"QRANK\" TEXT," + // 9: qrank
                "\"TOTAL\" REAL NOT NULL ," + // 10: total
                "\"ANNUAL\" TEXT);"); // 11: annual
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ENTERPRISE_CREDIT_JSON\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, EnterpriseCreditJson entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer oid = entity.getOid();
        if (oid != null) {
            stmt.bindLong(2, oid);
        }
 
        Integer eid = entity.getEid();
        if (eid != null) {
            stmt.bindLong(3, eid);
        }
 
        String unit_name = entity.getUnit_name();
        if (unit_name != null) {
            stmt.bindString(4, unit_name);
        }
 
        java.util.Date submit_date = entity.getSubmit_date();
        if (submit_date != null) {
            stmt.bindLong(5, submit_date.getTime());
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(6, status);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(7, type);
        }
 
        Integer cid = entity.getCid();
        if (cid != null) {
            stmt.bindLong(8, cid);
        }
 
        Integer ctstatus = entity.getCtstatus();
        if (ctstatus != null) {
            stmt.bindLong(9, ctstatus);
        }
 
        String qrank = entity.getQrank();
        if (qrank != null) {
            stmt.bindString(10, qrank);
        }
        stmt.bindDouble(11, entity.getTotal());
 
        String annual = entity.getAnnual();
        if (annual != null) {
            stmt.bindString(12, annual);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, EnterpriseCreditJson entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer oid = entity.getOid();
        if (oid != null) {
            stmt.bindLong(2, oid);
        }
 
        Integer eid = entity.getEid();
        if (eid != null) {
            stmt.bindLong(3, eid);
        }
 
        String unit_name = entity.getUnit_name();
        if (unit_name != null) {
            stmt.bindString(4, unit_name);
        }
 
        java.util.Date submit_date = entity.getSubmit_date();
        if (submit_date != null) {
            stmt.bindLong(5, submit_date.getTime());
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(6, status);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(7, type);
        }
 
        Integer cid = entity.getCid();
        if (cid != null) {
            stmt.bindLong(8, cid);
        }
 
        Integer ctstatus = entity.getCtstatus();
        if (ctstatus != null) {
            stmt.bindLong(9, ctstatus);
        }
 
        String qrank = entity.getQrank();
        if (qrank != null) {
            stmt.bindString(10, qrank);
        }
        stmt.bindDouble(11, entity.getTotal());
 
        String annual = entity.getAnnual();
        if (annual != null) {
            stmt.bindString(12, annual);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public EnterpriseCreditJson readEntity(Cursor cursor, int offset) {
        EnterpriseCreditJson entity = new EnterpriseCreditJson( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // oid
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // eid
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // unit_name
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)), // submit_date
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // status
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // type
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // cid
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // ctstatus
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // qrank
            cursor.getFloat(offset + 10), // total
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11) // annual
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, EnterpriseCreditJson entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOid(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setEid(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setUnit_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSubmit_date(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
        entity.setStatus(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setType(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setCid(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setCtstatus(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setQrank(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setTotal(cursor.getFloat(offset + 10));
        entity.setAnnual(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(EnterpriseCreditJson entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(EnterpriseCreditJson entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(EnterpriseCreditJson entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}