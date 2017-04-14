package net.xiaocun.entity;

/**
 * Created by zxiaocun on 2017/4/14.
 */
public class ResponseEntity<T> {

    public static enum StatusInfo
    {

        REQUEST_SUCCESS("200", "OK"),

        REQUEST_SUCCESS_ONLY_READ("201", "只有查看权限"),
        // 请求的参数不对，比如修改用户名，没有传入用户id
        REQUEST_ERROR("400", "请求参数异常，无法处理，请跟管理员联系"),
        // 权限拦截，无权限时的异常
        REQUEST_UNAUTHORIZED("401", "无权限进行此操作，请跟管理员联系"),
        // 未登录异常
        REQUEST_NOSESSION("402", "登录失效，请重新登录"),
        // 不合法的业务请求，比如直接删除管理有很多用户的用户组
        REQUEST_FORBIDDEN("403", "异常业务操作，不予处理，请跟管理员联系"),

        // 鉴权失败
        REQUEST_AUTHORIZE_FAILED("405","鉴权失败"),

        // 登陆鉴权失败
        REQUEST_LOGIN_AUTHORIZE_FAILED("406","登陆验证失败"),

        // 操作的资源不存在，比如 /user/{id}的增删查改等操作请求
        REQUEST_NORESOURCE("410", "请求的资源不存在"),
        // 所有内部异常，统一如此相应
        BUSINESS_FAILURE("500", "服务器忙，请稍后再次尝试，或者跟管理员联系");

        // 任务已审核结束
//		TASK_AUDIT_OVER("10010", "任务已审核结束"),
//		// 未勾选警示信息不允许打回
//		REJECT_NO_INFO("10011", "未勾选警示信息不允许打回");

        private final String code;
        private final String message;

        private StatusInfo(String code, String message)
        {
            this.code = code;
            this.message = message;
        }

        public String getCode()
        {
            return code;
        }

        public String getMessage()
        {
            return message;
        }

    }

    private T data;

    private String code;

    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatusInfo(StatusInfo statusInfo)
    {
        this.code = statusInfo.getCode();
        this.msg = statusInfo.getMessage();
    }
}
