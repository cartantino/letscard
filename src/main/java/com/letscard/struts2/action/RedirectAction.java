package com.letscard.struts2.action;

import com.opensymphony.xwork2.ActionSupport;

public class RedirectAction extends ActionSupport {

    public String JSPRedirect() {
        return ActionSupport.SUCCESS;
    }
}
