package com.minlength.platform.core.processor.sys.vo;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>通过JDBC查询数据的数据结果</p>
 * <br/>
 * @Title: JdbcQueryResult.java 
 * @Package com.meloclan.platform.core.processor.sys.impl.vo 
 * @author shugang
 * @date 2017 2017-5-14 下午6:14:21 
 * @version v1.0
 */
public class QueryResult extends Result{
		
	private long total;
	
	private List<Map<String, Object>> data;
	
	private Throwable exception;
		
	private int start;
	
	private int limit;
	
	public boolean isEmpty(){
		if(!this.isSuccess()){return true;}
		if(this.exception != null){return true;}
		if(this.data == null || this.data.size() == 0){return true;}
		return false;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
