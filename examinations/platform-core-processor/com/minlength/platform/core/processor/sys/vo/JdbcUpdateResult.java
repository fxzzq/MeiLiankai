package com.minlength.platform.core.processor.sys.vo;

public class JdbcUpdateResult extends Result{
		
	/**
	 * 每条sql执行影响的数据条数
	 */
	private int[] effectCounts;
	
	/**
	 * 如果是insert，并且数据库表ID为自动增长的，那么所有自动生成的keys值集合
	 */
	private long[] generatedKeys;
	
	/**
	 * 在执行过程中出现的错误
	 */
	private Throwable exception;

	public int[] getEffectCounts() {
		return effectCounts;
	}

	public void setEffectCounts(int[] effectCounts) {
		this.effectCounts = effectCounts;
	}

	public long[] getGeneratedKeys() {
		return generatedKeys;
	}

	public void setGeneratedKeys(long[] generatedKeys) {
		this.generatedKeys = generatedKeys;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}
	
}
