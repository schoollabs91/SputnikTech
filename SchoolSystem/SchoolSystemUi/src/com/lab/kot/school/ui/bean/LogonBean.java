package com.lab.kot.school.ui.bean;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class LogonBean {
    public LogonBean() {
        super();
    }
    String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    String password;
    public void doLogout(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("In LOgout ==========================================================");
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        try {
            System.out.println("FacesContext.getCurrentInstance().getExternalContext()"+FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
            FacesContext.getCurrentInstance().getExternalContext().redirect("/SchoolApps/faces/LoginPage");
        } catch (IOException ex) {
//            String error = LOGGER.error("error", ex);
        }
    }

    public String LoginAction() {
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operation = bindings.getOperationBinding("Login");
        operation.getParamsMap().put("userId", (String)this.getUserId());
        operation.getParamsMap().put("password", (String)this.getPassword());
        operation.execute();
        System.out.println("11111111");
        System.out.println("3333333333");
        System.out.println("operation.getResult()"+operation.getResult());
        if (operation.getResult() != null) {
            String result = (String) operation.getResult();
            if ("success".equalsIgnoreCase(result)) 
            {
                return "dialog";
            }
        }
        return null;
    }
}
