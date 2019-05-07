package com.zhilian.rf_qims.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhilian.rf_qims.entity.WorkAbilityJson;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WORK_ABILITY_JSON".
*/
public class WorkAbilityJsonDao extends AbstractDao<WorkAbilityJson, Long> {

    public static final String TABLENAME = "WORK_ABILITY_JSON";

    /**
     * Properties of entity WorkAbilityJson.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, Long.class, "ID", true, "_id");
        public final static Property WID = new Property(1, Long.class, "WID", false, "WID");
        public final static Property STATUS = new Property(2, int.class, "STATUS", false, "STATUS");
        public final static Property EID = new Property(3, Integer.class, "EID", false, "EID");
        public final static Property TESTDATE = new Property(4, String.class, "TESTDATE", false, "TESTDATE");
        public final static Property CONFIRMER = new Property(5, String.class, "CONFIRMER", false, "CONFIRMER");
        public final static Property VC = new Property(6, String.class, "VC", false, "VC");
        public final static Property CCAD = new Property(7, String.class, "CCAD", false, "CCAD");
        public final static Property TESTER = new Property(8, String.class, "TESTER", false, "TESTER");
        public final static Property ASSISTANT = new Property(9, String.class, "ASSISTANT", false, "ASSISTANT");
        public final static Property ADMINISTRATOR = new Property(10, String.class, "ADMINISTRATOR", false, "ADMINISTRATOR");
        public final static Property TOTAL = new Property(11, String.class, "TOTAL", false, "TOTAL");
        public final static Property ITEM = new Property(12, String.class, "ITEM", false, "ITEM");
        public final static Property ITEM1_1 = new Property(13, String.class, "ITEM1_1", false, "ITEM1_1");
        public final static Property ITEM1_2 = new Property(14, String.class, "ITEM1_2", false, "ITEM1_2");
        public final static Property ITEM1_3 = new Property(15, String.class, "ITEM1_3", false, "ITEM1_3");
        public final static Property ITEM1_4 = new Property(16, String.class, "ITEM1_4", false, "ITEM1_4");
        public final static Property ITEM2 = new Property(17, String.class, "ITEM2", false, "ITEM2");
        public final static Property ITEM2_1 = new Property(18, String.class, "ITEM2_1", false, "ITEM2_1");
        public final static Property ITEM2_2 = new Property(19, String.class, "ITEM2_2", false, "ITEM2_2");
        public final static Property ITEM2_3 = new Property(20, String.class, "ITEM2_3", false, "ITEM2_3");
        public final static Property ITEM2_4 = new Property(21, String.class, "ITEM2_4", false, "ITEM2_4");
        public final static Property ITEM2_5 = new Property(22, String.class, "ITEM2_5", false, "ITEM2_5");
        public final static Property ITEM2_6 = new Property(23, String.class, "ITEM2_6", false, "ITEM2_6");
        public final static Property ITEM2_7 = new Property(24, String.class, "ITEM2_7", false, "ITEM2_7");
        public final static Property ITEM2_8 = new Property(25, String.class, "ITEM2_8", false, "ITEM2_8");
        public final static Property ITEM3 = new Property(26, String.class, "ITEM3", false, "ITEM3");
        public final static Property ITEM3_1 = new Property(27, String.class, "ITEM3_1", false, "ITEM3_1");
        public final static Property ITEM3_2 = new Property(28, String.class, "ITEM3_2", false, "ITEM3_2");
        public final static Property ITEM3_3 = new Property(29, String.class, "ITEM3_3", false, "ITEM3_3");
        public final static Property ITEM4 = new Property(30, String.class, "ITEM4", false, "ITEM4");
        public final static Property WTYPE = new Property(31, String.class, "WTYPE", false, "WTYPE");
        public final static Property WCODE = new Property(32, String.class, "WCODE", false, "WCODE");
        public final static Property ENTERPRISE_NAME = new Property(33, String.class, "ENTERPRISE_NAME", false, "ENTERPRISE__NAME");
    }


    public WorkAbilityJsonDao(DaoConfig config) {
        super(config);
    }
    
    public WorkAbilityJsonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WORK_ABILITY_JSON\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: ID
                "\"WID\" INTEGER," + // 1: WID
                "\"STATUS\" INTEGER NOT NULL ," + // 2: STATUS
                "\"EID\" INTEGER," + // 3: EID
                "\"TESTDATE\" TEXT," + // 4: TESTDATE
                "\"CONFIRMER\" TEXT," + // 5: CONFIRMER
                "\"VC\" TEXT," + // 6: VC
                "\"CCAD\" TEXT," + // 7: CCAD
                "\"TESTER\" TEXT," + // 8: TESTER
                "\"ASSISTANT\" TEXT," + // 9: ASSISTANT
                "\"ADMINISTRATOR\" TEXT," + // 10: ADMINISTRATOR
                "\"TOTAL\" TEXT," + // 11: TOTAL
                "\"ITEM\" TEXT," + // 12: ITEM
                "\"ITEM1_1\" TEXT," + // 13: ITEM1_1
                "\"ITEM1_2\" TEXT," + // 14: ITEM1_2
                "\"ITEM1_3\" TEXT," + // 15: ITEM1_3
                "\"ITEM1_4\" TEXT," + // 16: ITEM1_4
                "\"ITEM2\" TEXT," + // 17: ITEM2
                "\"ITEM2_1\" TEXT," + // 18: ITEM2_1
                "\"ITEM2_2\" TEXT," + // 19: ITEM2_2
                "\"ITEM2_3\" TEXT," + // 20: ITEM2_3
                "\"ITEM2_4\" TEXT," + // 21: ITEM2_4
                "\"ITEM2_5\" TEXT," + // 22: ITEM2_5
                "\"ITEM2_6\" TEXT," + // 23: ITEM2_6
                "\"ITEM2_7\" TEXT," + // 24: ITEM2_7
                "\"ITEM2_8\" TEXT," + // 25: ITEM2_8
                "\"ITEM3\" TEXT," + // 26: ITEM3
                "\"ITEM3_1\" TEXT," + // 27: ITEM3_1
                "\"ITEM3_2\" TEXT," + // 28: ITEM3_2
                "\"ITEM3_3\" TEXT," + // 29: ITEM3_3
                "\"ITEM4\" TEXT," + // 30: ITEM4
                "\"WTYPE\" TEXT," + // 31: WTYPE
                "\"WCODE\" TEXT," + // 32: WCODE
                "\"ENTERPRISE__NAME\" TEXT);"); // 33: ENTERPRISE_NAME
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WORK_ABILITY_JSON\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, WorkAbilityJson entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        Long WID = entity.getWID();
        if (WID != null) {
            stmt.bindLong(2, WID);
        }
        stmt.bindLong(3, entity.getSTATUS());
 
        Integer EID = entity.getEID();
        if (EID != null) {
            stmt.bindLong(4, EID);
        }
 
        String TESTDATE = entity.getTESTDATE();
        if (TESTDATE != null) {
            stmt.bindString(5, TESTDATE);
        }
 
        String CONFIRMER = entity.getCONFIRMER();
        if (CONFIRMER != null) {
            stmt.bindString(6, CONFIRMER);
        }
 
        String VC = entity.getVC();
        if (VC != null) {
            stmt.bindString(7, VC);
        }
 
        String CCAD = entity.getCCAD();
        if (CCAD != null) {
            stmt.bindString(8, CCAD);
        }
 
        String TESTER = entity.getTESTER();
        if (TESTER != null) {
            stmt.bindString(9, TESTER);
        }
 
        String ASSISTANT = entity.getASSISTANT();
        if (ASSISTANT != null) {
            stmt.bindString(10, ASSISTANT);
        }
 
        String ADMINISTRATOR = entity.getADMINISTRATOR();
        if (ADMINISTRATOR != null) {
            stmt.bindString(11, ADMINISTRATOR);
        }
 
        String TOTAL = entity.getTOTAL();
        if (TOTAL != null) {
            stmt.bindString(12, TOTAL);
        }
 
        String ITEM = entity.getITEM();
        if (ITEM != null) {
            stmt.bindString(13, ITEM);
        }
 
        String ITEM1_1 = entity.getITEM1_1();
        if (ITEM1_1 != null) {
            stmt.bindString(14, ITEM1_1);
        }
 
        String ITEM1_2 = entity.getITEM1_2();
        if (ITEM1_2 != null) {
            stmt.bindString(15, ITEM1_2);
        }
 
        String ITEM1_3 = entity.getITEM1_3();
        if (ITEM1_3 != null) {
            stmt.bindString(16, ITEM1_3);
        }
 
        String ITEM1_4 = entity.getITEM1_4();
        if (ITEM1_4 != null) {
            stmt.bindString(17, ITEM1_4);
        }
 
        String ITEM2 = entity.getITEM2();
        if (ITEM2 != null) {
            stmt.bindString(18, ITEM2);
        }
 
        String ITEM2_1 = entity.getITEM2_1();
        if (ITEM2_1 != null) {
            stmt.bindString(19, ITEM2_1);
        }
 
        String ITEM2_2 = entity.getITEM2_2();
        if (ITEM2_2 != null) {
            stmt.bindString(20, ITEM2_2);
        }
 
        String ITEM2_3 = entity.getITEM2_3();
        if (ITEM2_3 != null) {
            stmt.bindString(21, ITEM2_3);
        }
 
        String ITEM2_4 = entity.getITEM2_4();
        if (ITEM2_4 != null) {
            stmt.bindString(22, ITEM2_4);
        }
 
        String ITEM2_5 = entity.getITEM2_5();
        if (ITEM2_5 != null) {
            stmt.bindString(23, ITEM2_5);
        }
 
        String ITEM2_6 = entity.getITEM2_6();
        if (ITEM2_6 != null) {
            stmt.bindString(24, ITEM2_6);
        }
 
        String ITEM2_7 = entity.getITEM2_7();
        if (ITEM2_7 != null) {
            stmt.bindString(25, ITEM2_7);
        }
 
        String ITEM2_8 = entity.getITEM2_8();
        if (ITEM2_8 != null) {
            stmt.bindString(26, ITEM2_8);
        }
 
        String ITEM3 = entity.getITEM3();
        if (ITEM3 != null) {
            stmt.bindString(27, ITEM3);
        }
 
        String ITEM3_1 = entity.getITEM3_1();
        if (ITEM3_1 != null) {
            stmt.bindString(28, ITEM3_1);
        }
 
        String ITEM3_2 = entity.getITEM3_2();
        if (ITEM3_2 != null) {
            stmt.bindString(29, ITEM3_2);
        }
 
        String ITEM3_3 = entity.getITEM3_3();
        if (ITEM3_3 != null) {
            stmt.bindString(30, ITEM3_3);
        }
 
        String ITEM4 = entity.getITEM4();
        if (ITEM4 != null) {
            stmt.bindString(31, ITEM4);
        }
 
        String WTYPE = entity.getWTYPE();
        if (WTYPE != null) {
            stmt.bindString(32, WTYPE);
        }
 
        String WCODE = entity.getWCODE();
        if (WCODE != null) {
            stmt.bindString(33, WCODE);
        }
 
        String ENTERPRISE_NAME = entity.getENTERPRISE_NAME();
        if (ENTERPRISE_NAME != null) {
            stmt.bindString(34, ENTERPRISE_NAME);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, WorkAbilityJson entity) {
        stmt.clearBindings();
 
        Long ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        Long WID = entity.getWID();
        if (WID != null) {
            stmt.bindLong(2, WID);
        }
        stmt.bindLong(3, entity.getSTATUS());
 
        Integer EID = entity.getEID();
        if (EID != null) {
            stmt.bindLong(4, EID);
        }
 
        String TESTDATE = entity.getTESTDATE();
        if (TESTDATE != null) {
            stmt.bindString(5, TESTDATE);
        }
 
        String CONFIRMER = entity.getCONFIRMER();
        if (CONFIRMER != null) {
            stmt.bindString(6, CONFIRMER);
        }
 
        String VC = entity.getVC();
        if (VC != null) {
            stmt.bindString(7, VC);
        }
 
        String CCAD = entity.getCCAD();
        if (CCAD != null) {
            stmt.bindString(8, CCAD);
        }
 
        String TESTER = entity.getTESTER();
        if (TESTER != null) {
            stmt.bindString(9, TESTER);
        }
 
        String ASSISTANT = entity.getASSISTANT();
        if (ASSISTANT != null) {
            stmt.bindString(10, ASSISTANT);
        }
 
        String ADMINISTRATOR = entity.getADMINISTRATOR();
        if (ADMINISTRATOR != null) {
            stmt.bindString(11, ADMINISTRATOR);
        }
 
        String TOTAL = entity.getTOTAL();
        if (TOTAL != null) {
            stmt.bindString(12, TOTAL);
        }
 
        String ITEM = entity.getITEM();
        if (ITEM != null) {
            stmt.bindString(13, ITEM);
        }
 
        String ITEM1_1 = entity.getITEM1_1();
        if (ITEM1_1 != null) {
            stmt.bindString(14, ITEM1_1);
        }
 
        String ITEM1_2 = entity.getITEM1_2();
        if (ITEM1_2 != null) {
            stmt.bindString(15, ITEM1_2);
        }
 
        String ITEM1_3 = entity.getITEM1_3();
        if (ITEM1_3 != null) {
            stmt.bindString(16, ITEM1_3);
        }
 
        String ITEM1_4 = entity.getITEM1_4();
        if (ITEM1_4 != null) {
            stmt.bindString(17, ITEM1_4);
        }
 
        String ITEM2 = entity.getITEM2();
        if (ITEM2 != null) {
            stmt.bindString(18, ITEM2);
        }
 
        String ITEM2_1 = entity.getITEM2_1();
        if (ITEM2_1 != null) {
            stmt.bindString(19, ITEM2_1);
        }
 
        String ITEM2_2 = entity.getITEM2_2();
        if (ITEM2_2 != null) {
            stmt.bindString(20, ITEM2_2);
        }
 
        String ITEM2_3 = entity.getITEM2_3();
        if (ITEM2_3 != null) {
            stmt.bindString(21, ITEM2_3);
        }
 
        String ITEM2_4 = entity.getITEM2_4();
        if (ITEM2_4 != null) {
            stmt.bindString(22, ITEM2_4);
        }
 
        String ITEM2_5 = entity.getITEM2_5();
        if (ITEM2_5 != null) {
            stmt.bindString(23, ITEM2_5);
        }
 
        String ITEM2_6 = entity.getITEM2_6();
        if (ITEM2_6 != null) {
            stmt.bindString(24, ITEM2_6);
        }
 
        String ITEM2_7 = entity.getITEM2_7();
        if (ITEM2_7 != null) {
            stmt.bindString(25, ITEM2_7);
        }
 
        String ITEM2_8 = entity.getITEM2_8();
        if (ITEM2_8 != null) {
            stmt.bindString(26, ITEM2_8);
        }
 
        String ITEM3 = entity.getITEM3();
        if (ITEM3 != null) {
            stmt.bindString(27, ITEM3);
        }
 
        String ITEM3_1 = entity.getITEM3_1();
        if (ITEM3_1 != null) {
            stmt.bindString(28, ITEM3_1);
        }
 
        String ITEM3_2 = entity.getITEM3_2();
        if (ITEM3_2 != null) {
            stmt.bindString(29, ITEM3_2);
        }
 
        String ITEM3_3 = entity.getITEM3_3();
        if (ITEM3_3 != null) {
            stmt.bindString(30, ITEM3_3);
        }
 
        String ITEM4 = entity.getITEM4();
        if (ITEM4 != null) {
            stmt.bindString(31, ITEM4);
        }
 
        String WTYPE = entity.getWTYPE();
        if (WTYPE != null) {
            stmt.bindString(32, WTYPE);
        }
 
        String WCODE = entity.getWCODE();
        if (WCODE != null) {
            stmt.bindString(33, WCODE);
        }
 
        String ENTERPRISE_NAME = entity.getENTERPRISE_NAME();
        if (ENTERPRISE_NAME != null) {
            stmt.bindString(34, ENTERPRISE_NAME);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public WorkAbilityJson readEntity(Cursor cursor, int offset) {
        WorkAbilityJson entity = new WorkAbilityJson( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // WID
            cursor.getInt(offset + 2), // STATUS
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // EID
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // TESTDATE
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // CONFIRMER
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // VC
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // CCAD
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // TESTER
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // ASSISTANT
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // ADMINISTRATOR
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // TOTAL
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // ITEM
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // ITEM1_1
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // ITEM1_2
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // ITEM1_3
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // ITEM1_4
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // ITEM2
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // ITEM2_1
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // ITEM2_2
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // ITEM2_3
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // ITEM2_4
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // ITEM2_5
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // ITEM2_6
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // ITEM2_7
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // ITEM2_8
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // ITEM3
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // ITEM3_1
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // ITEM3_2
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // ITEM3_3
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // ITEM4
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // WTYPE
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // WCODE
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33) // ENTERPRISE_NAME
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, WorkAbilityJson entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setWID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setSTATUS(cursor.getInt(offset + 2));
        entity.setEID(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setTESTDATE(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCONFIRMER(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setVC(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCCAD(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTESTER(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setASSISTANT(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setADMINISTRATOR(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setTOTAL(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setITEM(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setITEM1_1(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setITEM1_2(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setITEM1_3(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setITEM1_4(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setITEM2(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setITEM2_1(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setITEM2_2(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setITEM2_3(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setITEM2_4(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setITEM2_5(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setITEM2_6(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setITEM2_7(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setITEM2_8(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setITEM3(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setITEM3_1(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setITEM3_2(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setITEM3_3(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setITEM4(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setWTYPE(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setWCODE(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setENTERPRISE_NAME(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(WorkAbilityJson entity, long rowId) {
        entity.setID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(WorkAbilityJson entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(WorkAbilityJson entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}