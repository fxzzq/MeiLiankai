package com.minlength.platform.core.web.processor.vo;

import com.minlength.platform.core.processor.sys.vo.Result;

/**
 * 返回到页面的数据格式
 * @author shugang
 *
 */
public class ResponseResult extends Result {
	
	protected Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
