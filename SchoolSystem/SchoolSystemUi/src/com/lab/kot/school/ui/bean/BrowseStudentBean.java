package com.lab.kot.school.ui.bean;

import com.lab.kot.school.ui.handler.utility.JSFUtil;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.nav.RichButton;

public class BrowseStudentBean {
    private RichTable studentTable;

    public BrowseStudentBean() {
        super();
    }

    public void FilterActionListener(ActionEvent actionEvent) {
        UIComponent ui = actionEvent.getComponent();
        String text = ((RichButton)ui).getText();
        JSFUtil.setExpressionValue("#{pageFlowScope.select}", text);
        JSFUtil.invokeEL("#{bindings.ExecuteWithParams1.execute}");
        JSFUtil.refreshUIComponent(studentTable);
    }

    public void setStudentTable(RichTable studentTable) {
        this.studentTable = studentTable;
    }

    public RichTable getStudentTable() {
        return studentTable;
    }
}
