package com.colin.dispatcher;

/**
 * Created by colin on 2018/1/18.
 */

public interface GrantableRequest extends PermissionRequest {

	void grant();
}

