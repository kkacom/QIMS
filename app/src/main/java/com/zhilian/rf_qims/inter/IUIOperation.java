package com.zhilian.rf_qims.inter;

/**
 * 封装ui相关的操作
 */
public interface IUIOperation {

	/** 返回activity的布局文件 */
	int getLayoutRes();
	
	/** 查找子控件 */
	void initView() ;
	
	/** 设置监听器 */
	void initListener() ;
	
	/** 初始化数据 */
	void initData() ;
	
}

