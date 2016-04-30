package com.ai.opt.sol.web.base.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class PrintWriterUtil {

    public static void write(HttpServletResponse response, String data) throws Exception {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(data);
        printWriter.flush();
        printWriter.close();
    }

}
