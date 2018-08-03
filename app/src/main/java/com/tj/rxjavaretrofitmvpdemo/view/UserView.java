package com.tj.rxjavaretrofitmvpdemo.view;

import com.tj.rxjavaretrofitmvpdemo.base.BaseView;
import com.tj.rxjavaretrofitmvpdemo.modle.bean.UserInfo;

import java.util.List;

/**
 * @作者: TJ
 * @时间: 2018/8/2 16:54
 * @描述: 将MainPresenter的数据传到View层，然后有V层设置视图
 */
public interface UserView extends BaseView {

    void setData(UserInfo userInfo);
}
