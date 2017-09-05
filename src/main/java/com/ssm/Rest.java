package com.ssm;

import com.ssm.dto.Const;
import lombok.Data;

/**
 * 
 * @author kezhuang.li
 *
 * @param <T>
 */
@Data
public class Rest<T> {

	private int code;
	private T data;
	private String msg;

	private Rest(int code, T data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	public static <T> Rest<T> json(int code, T data, String msg) {
		return new Rest<T>(code, data, msg);
	}

	public static <T> Rest<T> ok(T data, String msg) {
		return json(Const.ERR_CODE_OK, data, msg);
	}

	public static <T> Rest<T> ok(T data) {
		return json(Const.ERR_CODE_OK, data, null);
	}

	public static <T> Rest<T> ok() {
		return json(Const.ERR_CODE_OK, null, null);
	}

	public static <T> Rest<T> fail() {
		return json(Const.ERR_CODE_FAIL, null, null);
	}

	public static <T> Rest<T> fail(String msg) {
		return json(Const.ERR_CODE_FAIL, null, msg);
	}

	public static <T> Rest<T> msg(int code, String msg) {
		return json(code, null, msg);
	}

	public static <T> Rest<T> msg(int code) {
		return json(code, null, null);
	}
	
	public static <T> Rest<T> forbidden() {
		return json(Const.ERR_CODE_FORBIDDEN, null, "Forbidden");
	}
	
	public static <T> Rest<T> notFound() {
		return json(Const.ERR_CODE_NOT_FOUND, null, "Not Found");
	}
	
	public static <T> Rest<T> serverError(String msg) {
		return json(Const.ERR_CODE_SERVER_ERROR, null, msg);
	}
	
	public static <T> Rest<T> serverError() {
		return json(Const.ERR_CODE_SERVER_ERROR, null, "HTTP-Internal Server Error");
	}

}
