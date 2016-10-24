package com.jzs.arc.utils;

public class ConstantFields {
    /* 管理员登陆的KEY */
    public static final String SESSION_ADMIN_KEY = "adminLogin";
    public static final String SESSION_AUTHORITY_KEY = "authoritys";
    public static final String SESSION_FUNCTION_KEY = "functions";
    public static final String ADMIN_KEY = "admin";

    /* 分页中每页数据数 */
    public static final int DEFAULT_PAGE_SIZE = 20;
    /* ajax分页中数据的KEY */
    public static final String PAGE_KEY = "page";
    /* 日志查询时存储LogContent条件的Session Key */
    public static final String SESSION_LOG_SEARCH_KEY = "logSearch";
    /* 设备查询时存储设备条件的Session Key */
    public static final String SESSION_DEVICE_SEARCH_KEY = "deviceSearch";
    /* 防护维修查询时查询条件的Session Key */
    public static final String SESSION_MAINTAIN_SEARCH_KEY = "maintainSearch";
    /* 防护维修查询时查询条件的Session Key */
    public static final String SESSION_PROTECT_SEARCH_KEY = "portectSearch";
    /* 防护维修查询时查询条件的Session Key */
    public static final String SESSION_INSPECTION_SEARCH_KEY = "instpectionSearch";
    /* 防护维修查询全部时查询条件的Session Key */
    public static final String SESSION_ALL_SEARCH_KEY = "allMaintainSearch";
    /**
     * 默认邮件配置项
     */
    public static final String MAIL_DEFAULT_HOST_KEY = "mail.smtp.host";
    public static final String MAIL_DEFAULT_AUTH_ENABLE_KEY = "mail.smtp.auth";
    public static final String MAIL_DEFAULT_PORT_KEY = "mail.smtp.port";
    public static final String MAIL_DEFAULT_USER_KEY = "mail.smtp.user";
    public static final String MAIL_DEFAULT_USER_PASS_KEY = "mail.smtp.pass";
    public static final String MAIL_DEFAULT_STARTTLS_ENABLE_KEY = "mail.smtp.starttls.enable";

    /* 退出登录异常key */
    public static final String EXIT_INSPECTION_KEY = "exitInspectionMessage";
    /* 退出登录异常消息 */
    public static final String EXIT_INSPECTION_MESSAGE = "还存在未结束的巡检！";
    /* 添加账户不唯一 */
    public static final String ADD_NOTUNIQUE_KEY = "addNotNuiqueMessage";
    /* 添加账户不唯一消息 */
    public static final String ADD_NOTUNIQUE_MESSAGE = "已存在该账户！";
    /* 操作成功消息Key */
    public static final String OPREATE_SUCCESS_KEY = "opreateMessage";
    /* 操作成功消息 */
    public static final String OPREATE_SUCCESS_MESSAGE = "操作成功";
    /* 操作失败消息Key */
    public static final String OPREATE_FAILURE_KEY = "opreateFailureMessage";
    /* 操作失败消息 */
    public static final String OPREATE_FAILURE_MESSAGE = "操作失败";
    /* 添加上班人员消息失败 */
    public static final String WOEKERINFO_ADD_FAILURE_MESSAGE = "今天上班人员信息已添加";
    /* 添加山板人员消息失败Key */
    public static final String WOEKERINFO_ADD_FAILURE_KEY = "workerInfoAddMessage";
    /* 撤销重复消息Key */
    public static final String CANCLE_REPI_KEY = "cancleRepiMessage";
    /* 撤销重复消息 */
    public static final String CANCLE_REPI_MESSAGE = "撤销重复";
    /* 撤销成功消息Key */
    public static final String CANCLE_SUCCESS_KEY = "cancleMessage";
    /* 撤销成功消息 */
    public static final String CANCLE_SUCCESS_MESSAGE = "撤销成功";
    /* 撤销失败消息Key */
    public static final String CANCLE_FAILURE_KEY = "cancleFailureMessage";
    /* 撤销失败消息 */
    public static final String CANCLE_FAILURE_MESSAGE = "撤销失败";
    /* 添加成功消息Key */
    public static final String ADD_SUCCESS_KEY = "addMessage";
    /* 添加成功消息 */
    public static final String ADD_SUCCESS_MESSAGE = "添加成功";
    /* 添加重复消息Key */
    public static final String ADD_REPEAT_KEY = "addRepeatMessage";
    /* 添加重复消息 */
    public static final String ADD_REPEAT_MESSAGE = "已存在该设备";
    /* 添加失败消息Key */
    public static final String ADD_FAILURE_KEY = "addFailureMessage";
    /* 添加失败消息 */
    public static final String ADD_FAILURE_MESSAGE = "添加失败";
    /* 编辑成功消息Key */
    public static final String EDIT_SUCCESS_KEY = "editMessage";
    /* 编辑成功消息 */
    public static final String EDIT_SUCCESS_MESSAGE = "编辑成功";
    /* 编辑失败消息Key */
    public static final String EDIT_FAILURE_KEY = "editFailureMessage";
    /* 编辑失败消息 */
    public static final String EDIT_FAILURE_MESSAGE = "编辑失败";
    /* 删除成功消息Key */
    public static final String DELETE_SUCCESS_KEY = "deleteMessage";
    /* 删除成功消息 */
    public static final String DELETE_SUCCESS_MESSAGE = "删除成功";
    /* 删除失败消息Key */
    public static final String DELETE_FAILURE_KEY = "deleteFailureMessage";
    /* 删除失败消息 */
    public static final String DELETE_FAILURE_MESSAGE = "删除失败";
    /* 升职成功消息Key */
    public static final String PROMOTE_SUCCESS_KEY = "promoteMessage";
    /* 升职成功消息 */
    public static final String PROMOTE_SUCCESS_MESSAGE = "升职成功";
    /* 升职失败消息Key */
    public static final String PROMOTE_FAILURE_KEY = "promoteFailureMessage";
    /* 升职失败消息 */
    public static final String PROMOTE_FAILURE_MESSAGE = "升职失败";
    /* 防护成功消息Key */
    public static final String PROTECT_SUCCESS_KEY = "protectMessage";
    /* 防护成功消息 */
    public static final String PROTECT_SUCCESS_MESSAGE = "防护成功";
    /* 取消防护成功消息Key */
    public static final String CANCLE_PROTECT_SUCCESS_KEY = "cancleProtectMessage";
    /* 取消防护成功消息 */
    public static final String CANCLE_PROTECT_SUCCESS_MESSAGE = "取消防护成功";
    /* 取消防护异常消息Key */
    public static final String CANCLE_PROTECT_REPEAT_KEY = "cancleProtectRepeatMessage";
    /*取消防护异常消息 */
    public static final String CANCLWPROTECT_REPEAT_MESSAGE = "该防护还有故障未处理";
    /* 取消防护失败消息Key */
    public static final String CANCLE_PROTECT_FAILURE_KEY = "cancleProtectFailureMessage";
    /* 取消防护失败消息 */
    public static final String CANCLE_PROTECT_FAILURE_MESSAGE = "取消防护失败";
    /* 防护失败消息Key */
    public static final String PROTECT_FAILURE_KEY = "protectFailureMessage";
    /* 防护失败消息 */
    public static final String PROTECT_FAILURE_MESSAGE = "防护失败";
    /* 登记成功消息Key */
    public static final String MAINTAIN_SUCCESS_KEY = "maintainMessage";
    /* 登记成功消息 */
    public static final String MAINTAIN_SUCCESS_MESSAGE = "登记成功";
    /* 登记重复消息Key */
    public static final String MAINTAIN_REPEAT_KEY = "maintainRepeatMessage";
    /* 登记重复消息 */
    public static final String MAINTAIN_REPEAT_MESSAGE = "该故障已登记";
    /* 登记失败消息Key */
    public static final String MAINTAIN_FAILURE_KEY = "maintainFailureMessage";
    /* 登记失败消息 */
    public static final String MAINTAIN_FAILURE_MESSAGE = "登记失败";
    /* 销记成功消息Key */
    public static final String MAINTAIN_FINISH_SUCCESS_KEY = "maintainFinishMessage";
    /* 销记成功消息 */
    public static final String MAINTAIN_FINISH_SUCCESS_MESSAGE = "销记成功";
    /* 销记失败消息Key */
    public static final String MAINTAIN_FINISH_FAILURE_KEY = "maintainFinishFailureMessage";
    /* 销记失败消息 */
    public static final String MAINTAIN_FINISH_FAILURE_MESSAGE = "销记失败";
    /* 防护重复消息Key */
    public static final String PROTECT_REPEAT_KEY = "protectRepeatMessage";
    /* 防护重复消息 */
    public static final String PROTECT_REPEAT_MESSAGE = "该轨道已在防护中";
    /* 防护未处理完消息Key */
    public static final String PROTECT_REMAIN_KEY = "protectRemainMessage";
    /* 防护未处理完消息 */
    public static final String PROTECT_REMAIN_MESSAGE = "该故障流程中还有防护未取消";

    public static final String UPLOAD_EXCEL_FILE_NAME ="targetFile";
}
