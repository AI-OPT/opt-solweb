package com.ai.opt.sol.core.sandbox.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.ai.opt.sol.core.constants.OspConstants;

public class OspSandboxUtil {

    /**
     * 根据参数的类型获取编辑器类型
     * 
     * @param javaType
     * @return
     * @author zhangchao
     */
    public static String getEditorType(String javaType) {
        if ("void".equals(javaType)) {
            return OspConstants.EditorType.EDITOR_TYPE_NONE;
        }
        boolean transfer2json = ReflectUtil.checkReflectMap(javaType);
        return transfer2json ? OspConstants.EditorType.EDITOR_TYPE_JSON
                : OspConstants.EditorType.EDITOR_TYPE_TEXT;
    }

    /**
     * 获取百分比
     * 
     * @param num
     * @param total
     * @param scale
     * @return
     * @author zhangchao
     */
    public static String getPercent(double num, double total, int scale) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        // 可以设置精确几位小数
        df.setMaximumFractionDigits(scale);
        // 模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        double accuracy_num = num / total * 100;
        return df.format(accuracy_num) + "%";
    }

    public static void main(String[] agrs) {
        System.out.print(getPercent(50, 70, 2));
    }

}
