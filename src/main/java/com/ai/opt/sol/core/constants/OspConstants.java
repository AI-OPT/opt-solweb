package com.ai.opt.sol.core.constants;

public final class OspConstants {
    private OspConstants() {
    }

    public static final class EditorType {
        // 编辑器类型：JSON编辑器
        public static final String EDITOR_TYPE_JSON = "1";

        // 编辑器类型：普通文本
        public static final String EDITOR_TYPE_TEXT = "2";

        // 编辑器类型：不需要编辑器
        public static final String EDITOR_TYPE_NONE = "3";
    }

    public static final class CallType {
        // 沙箱调用
        public static final String CALL_TYPE_SANDBOX = "1";

        // 直接调用
        public static final String CALL_TYPE_DIRECT = "2";
    }

    public static final class ExceptionCode {
        private ExceptionCode() {
        }

        public static final String SUCCESS = "000000";// 成功

        public static final String SYSTEM_ERROR = "999999";// 系统错误

        public static final String PARAM_IS_NULL = "888888";// 参数为空

        public static final String NO_RESULT = "000001";// 无结果

        public static final String PARAM_TYPE_NOT_RIGHT = "000002";// 参数类型错误

        public static final String NO_DATA_OR_CACAE_ERROR = "000003";// 无数据或缓存错误

        public static final String PARAM_VALUE_NOT_RIGHT = "000004";// 参数取值错误

        public static final String PARAM_VALUE_EXIST_ERROR = "000005";// 参数值重复错误

        public static final String RESULT_IS_NULL = "000006";// 结果为空
    }

    public static final class FeedbackState {

        public static final String INIT = "01";

        public static final String DONE = "02";

    }
    
    public static final class UpgState {
        
        //进行中
        public static final String ING = "1";
        
        //取消
        public static final String CANCEL = "2";
    }

}
