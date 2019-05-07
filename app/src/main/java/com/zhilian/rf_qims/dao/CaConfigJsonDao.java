package com.zhilian.rf_qims.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhilian.rf_qims.entity.CaConfigJson;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CA_CONFIG_JSON".
*/
public class CaConfigJsonDao extends AbstractDao<CaConfigJson, Long> {

    public static final String TABLENAME = "CA_CONFIG_JSON";

    /**
     * Properties of entity CaConfigJson.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "ID", true, "_id");
        public final static Property SN = new Property(1, String.class, "SN", false, "SN");
        public final static Property TITLE = new Property(2, String.class, "TITLE", false, "TITLE");
        public final static Property SCORE = new Property(3, String.class, "SCORE", false, "SCORE");
        public final static Property NUMBER = new Property(4, Integer.class, "NUMBER", false, "NUMBER");
        public final static Property EACH = new Property(5, String.class, "EACH", false, "EACH");
        public final static Property MEMO = new Property(6, String.class, "MEMO", false, "MEMO");
        public final static Property NAME = new Property(7, String.class, "NAME", false, "NAME");
        public final static Property TYPE = new Property(8, Integer.class, "TYPE", false, "TYPE");
        public final static Property WETYPE = new Property(9, Integer.class, "WETYPE", false, "WETYPE");
        public final static Property LEVEL = new Property(10, Integer.class, "LEVEL", false, "LEVEL");
        public final static Property VIEW_TYPE = new Property(11, Integer.class, "VIEW_TYPE", false, "VIEW__TYPE");
        public final static Property COMMON_TYPE = new Property(12, Integer.class, "COMMON_TYPE", false, "COMMON__TYPE");
    }


    public CaConfigJsonDao(DaoConfig config) {
        super(config);
    }
    
    public CaConfigJsonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CA_CONFIG_JSON\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: ID
                "\"SN\" TEXT," + // 1: SN
                "\"TITLE\" TEXT," + // 2: TITLE
                "\"SCORE\" TEXT," + // 3: SCORE
                "\"NUMBER\" INTEGER," + // 4: NUMBER
                "\"EACH\" TEXT," + // 5: EACH
                "\"MEMO\" TEXT," + // 6: MEMO
                "\"NAME\" TEXT," + // 7: NAME
                "\"TYPE\" INTEGER," + // 8: TYPE
                "\"WETYPE\" INTEGER," + // 9: WETYPE
                "\"LEVEL\" INTEGER," + // 10: LEVEL
                "\"VIEW__TYPE\" INTEGER," + // 11: VIEW_TYPE
                "\"COMMON__TYPE\" INTEGER);"); // 12: COMMON_TYPE
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CA_CONFIG_JSON\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CaConfigJson entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String SN = entity.getSN();
        if (SN != null) {
            stmt.bindString(2, SN);
        }
 
        String TITLE = entity.getTITLE();
        if (TITLE != null) {
            stmt.bindString(3, TITLE);
        }
 
        String SCORE = entity.getSCORE();
        if (SCORE != null) {
            stmt.bindString(4, SCORE);
        }
 
        Integer NUMBER = entity.getNUMBER();
        if (NUMBER != null) {
            stmt.bindLong(5, NUMBER);
        }
 
        String EACH = entity.getEACH();
        if (EACH != null) {
            stmt.bindString(6, EACH);
        }
 
        String MEMO = entity.getMEMO();
        if (MEMO != null) {
            stmt.bindString(7, MEMO);
        }
 
        String NAME = entity.getNAME();
        if (NAME != null) {
            stmt.bindString(8, NAME);
        }
 
        Integer TYPE = entity.getTYPE();
        if (TYPE != null) {
            stmt.bindLong(9, TYPE);
        }
 
        Integer WETYPE = entity.getWETYPE();
        if (WETYPE != null) {
            stmt.bindLong(10, WETYPE);
        }
 
        Integer LEVEL = entity.getLEVEL();
        if (LEVEL != null) {
            stmt.bindLong(11, LEVEL);
        }
 
        Integer VIEW_TYPE = entity.getVIEW_TYPE();
        if (VIEW_TYPE != null) {
            stmt.bindLong(12, VIEW_TYPE);
        }
 
        Integer COMMON_TYPE = entity.getCOMMON_TYPE();
        if (COMMON_TYPE != null) {
            stmt.bindLong(13, COMMON_TYPE);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CaConfigJson entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String SN = entity.getSN();
        if (SN != null) {
            stmt.bindString(2, SN);
        }
 
        String TITLE = entity.getTITLE();
        if (TITLE != null) {
            stmt.bindString(3, TITLE);
        }
 
        String SCORE = entity.getSCORE();
        if (SCORE != null) {
            stmt.bindString(4, SCORE);
        }
 
        Integer NUMBER = entity.getNUMBER();
        if (NUMBER != null) {
            stmt.bindLong(5, NUMBER);
        }
 
        String EACH = entity.getEACH();
        if (EACH != null) {
            stmt.bindString(6, EACH);
        }
 
        String MEMO = entity.getMEMO();
        if (MEMO != null) {
            stmt.bindString(7, MEMO);
        }
 
        String NAME = entity.getNAME();
        if (NAME != null) {
            stmt.bindString(8, NAME);
        }
 
        Integer TYPE = entity.getTYPE();
        if (TYPE != null) {
            stmt.bindLong(9, TYPE);
        }
 
        Integer WETYPE = entity.getWETYPE();
        if (WETYPE != null) {
            stmt.bindLong(10, WETYPE);
        }
 
        Integer LEVEL = entity.getLEVEL();
        if (LEVEL != null) {
            stmt.bindLong(11, LEVEL);
        }
 
        Integer VIEW_TYPE = entity.getVIEW_TYPE();
        if (VIEW_TYPE != null) {
            stmt.bindLong(12, VIEW_TYPE);
        }
 
        Integer COMMON_TYPE = entity.getCOMMON_TYPE();
        if (COMMON_TYPE != null) {
            stmt.bindLong(13, COMMON_TYPE);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CaConfigJson readEntity(Cursor cursor, int offset) {
        CaConfigJson entity = new CaConfigJson( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // SN
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // TITLE
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // SCORE
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // NUMBER
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // EACH
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // MEMO
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // NAME
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // TYPE
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // WETYPE
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // LEVEL
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // VIEW_TYPE
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12) // COMMON_TYPE
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CaConfigJson entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSN(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTITLE(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSCORE(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNUMBER(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setEACH(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMEMO(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setNAME(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTYPE(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setWETYPE(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setLEVEL(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setVIEW_TYPE(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setCOMMON_TYPE(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CaConfigJson entity, long rowId) {
        entity.setID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CaConfigJson entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CaConfigJson entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
