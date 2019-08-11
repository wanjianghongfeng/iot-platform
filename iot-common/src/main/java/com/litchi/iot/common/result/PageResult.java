package com.litchi.iot.common.result;

import java.io.Serializable;
import java.util.List;

/** 
 * 类说明
 * @author: tievd(wjhf)
 * @date: 2019年8月10日 下午11:39:18
 * @vesion: 0.0.1
 */
public class PageResult<T> extends Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 总记录数 */
	private long total;
	/** 列表数据 */
	private List<?> rows;

	/**
	 * 表格数据对象
	 */
	public PageResult() {
	}

	/**
	 * 分页
	 * 
	 * @param list
	 *            列表数据
	 * @param total
	 *            总记录数
	 */
	public PageResult(List<?> list, long total) {
		this.rows = list;
		this.total = total;
		super.ok();
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
