package com.keduit.board.config;

public class AjaxUtils {

    public static boolean isAjaxRequest(String requestedWith) {
        return "XMLHttpRequest".equals(requestedWith);
    }

}
