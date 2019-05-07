package com.zhilian.rf_qims.dao;

import com.zhilian.rf_qims.entity.Area;
import com.zhilian.rf_qims.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by sl on 2017-10-17.
 */
public interface IDBRxManager{
	void saveUserInfo(UserInfo user);
	Observable<UserInfo> queryUserInfo(long id);
	Observable<List<Area>> findLocalAreas();
}
