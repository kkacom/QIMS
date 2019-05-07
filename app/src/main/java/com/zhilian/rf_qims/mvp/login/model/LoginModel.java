package com.zhilian.rf_qims.mvp.login.model;

import com.colin.constant.Constants;
import com.colin.http.HttpServiceManager;
import com.colin.utils.LogUtil;
import com.zhilian.rf_qims.api.Pwd;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.UserInfoDao;
import com.zhilian.rf_qims.entity.UserInfo;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;



/**
 * Created by Administrator on 2017-9-22.
 */
public class LoginModel implements ILoginModel {
    @Override
    public void confirmUser(final UserInfo user, final LoginCallBack callBack) {
        HttpServiceManager.getInstance().getHttpService()
                .login(user.getLoginName() + "-" + user.getPwd())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result = responseBody.string();
                        //Log.d("login", result);
                        JSONObject object = new JSONObject(result);
                        String status = object.getString("status");
                        if (status.equals("1")){
                            String limit = object.getString("startReceive");
                            String accessToken = object.getString("msg");
                            String userName = object.getString("uname");
                            String userId = object.getString("id");
                            //String userName = object.getString("name");
                            Constants.limit = limit;
                            Constants.accessToken = accessToken;
                            Constants.userName = userName;
                            Constants.userId = userId;
                            UserInfoDao dao = GreenDaoManager.getInstance().getNewSession().getUserInfoDao();
                            dao.deleteAll();
                            UserInfo userInfo=new UserInfo(0,0,userName,user.getLoginName(),
                                accessToken,Pwd.encrypt(user.getPwd()),"",limit);
                            //UserInfo userInfo=new UserInfo(0,0,userName,"",accessToken,"","",limit);
//                            UserInfo cache = dao.load(0L);
//                            if (null != cache) {
//                                dao.update(user);
//                            } else {
                                dao.insert(userInfo);
//                            }
                            callBack.onSuccess();
                        }else {
                            String errmsg = object.getString("msg");
                            callBack.onFailure(errmsg);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //callBack.onSuccess();//无网络时需测试登录
                        callBack.onFailure("登录失败");
                        LogUtil.e("login-error:" + throwable.getMessage());
                    }
                });
    }


    @Override
    public void forgotPwd() {

    }

    @Override
    public void loadCacheUserInfo(LoginCallBack callBack) {
        UserInfoDao dao = GreenDaoManager.getInstance().getNewSession().getUserInfoDao();
        QueryBuilder<UserInfo> builder = dao.queryBuilder();
        List<UserInfo> list = builder.list();
        if (null != list && list.size() > 0) {
            UserInfo info = list.get(0);
//            confirmUser(info, callBack);
            Constants.limit = info.getLimit();
            Constants.accessToken =info.getAccessToken();
            Constants.userName = info.getLoginName();
            callBack.onSuccess();
        }
    }

    @Override
    public int getCacheUserInfoSize() {
        UserInfoDao dao = GreenDaoManager.getInstance().getNewSession().getUserInfoDao();
        QueryBuilder<UserInfo> builder = dao.queryBuilder();
        List<UserInfo> list = builder.list();
        if (null != list && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

}
