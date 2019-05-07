package com.zhilian.rf_qims.dao;


import com.zhilian.rf_qims.entity.Area;
import com.zhilian.rf_qims.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sl on 2017-10-17.
 */
public class DBRxManager implements IDBRxManager{

	private static DBRxManager instance = null;

	public synchronized static DBRxManager getInstance() {
		return null == instance ? new DBRxManager() : instance;
	}

	private DBRxManager() {

	}

	@Override
	public void saveUserInfo(UserInfo user) {
		UserInfoDao dao = GreenDaoManager.getInstance().getNewSession().getUserInfoDao();
		UserInfo userCache = dao.load(1L);
		if (null == userCache) {
			dao.insert(user);
		} else {
			if (user.getId() == userCache.getId()) {
				dao.update(user);
			}
		}
	}

	@Override
	public Observable<UserInfo> queryUserInfo(long id) {
		return Observable.create(new ObservableOnSubscribe<UserInfo>(){
			@Override
			public void subscribe(@NonNull ObservableEmitter<UserInfo> e) throws Exception {
				UserInfo userInfo = GreenDaoManager.getInstance().getNewSession().getUserInfoDao().load(1L);
				if (null != userInfo) e.onNext(userInfo);
			}
		}).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
	}

	@Override
	public Observable<List<Area>> findLocalAreas() {
		return Observable.create(new ObservableOnSubscribe<List<Area>>() {
			@Override
			public void subscribe(ObservableEmitter<List<Area>> e) throws Exception {
				List<Area> areas = GreenDaoManager.getInstance().getNewSession().getAreaDao().loadAll();
				if (!areas.isEmpty()){
					e.onNext(areas);
				}
			}
		}).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
	}


}
