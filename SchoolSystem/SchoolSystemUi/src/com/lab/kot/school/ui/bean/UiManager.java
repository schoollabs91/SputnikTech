package com.lab.kot.school.ui.bean;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.view.rich.component.rich.RichPopup;

public class UiManager implements Serializable {
    private String taskFlowId = "/WEB-INF/StudentBTF.xml#StudentBTF";

    public UiManager() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDynamicTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }

    public String studentAttendBTF() {
        setDynamicTaskFlowId("/WEB-INF/StudentAttendBTF.xml#StudentAttendBTF");
        return null;
    }

    public String noticeBoardBTF() {
        setDynamicTaskFlowId("/WEB-INF/NoticeBoardBTF.xml#NoticeBoardBTF");
        return null;
    }

    public String teacherBTF() {
        setDynamicTaskFlowId("/WEB-INF/TeacherBTF.xml#TeacherBTF");
        return null;
    }

    public String timeTableBTF() {
        setDynamicTaskFlowId("/WEB-INF/TimeTableBTF.xml#TimeTableBTF");
        return null;
    }

    public void ClosePopupActionListener(ActionEvent actionEvent) {
        UIComponent ui =actionEvent.getComponent();
        UIComponent ui1= ui.getParent();
        UIComponent ui2= ui1.getParent();
        RichPopup popup =(RichPopup)ui2.getParent();
        popup.cancel();
    }

    public String studentBTF() {
        setDynamicTaskFlowId("/WEB-INF/StudentBTF.xml#StudentBTF");
        return null;
    }

    public String teacherAttendBTF() {
        setDynamicTaskFlowId("/WEB-INF/TeacherAttendBTF.xml#TeacherAttendBTF");
        return null;
    }

    public String bookStoreBTF() {
        setDynamicTaskFlowId("/WEB-INF/BookStoreBTF.xml#BookStoreBTF");
        return null;
    }

    public String bookReturnBTF() {
        setDynamicTaskFlowId("/WEB-INF/BookReturnBTF.xml#BookReturnBTF");
        return null;
    }

    public String listOfValuesBTF() {
        setDynamicTaskFlowId("/WEB-INF/ListOfValuesBTF.xml#ListOfValuesBTF");
        return null;
    }

    public String browseStudents() {
        setDynamicTaskFlowId("/WEB-INF/BrowseStudents.xml#BrowseStudents");
        return null;
    }

    public String configureUsersBTF() {
        setDynamicTaskFlowId("/WEB-INF/ConfigureUsersBTF.xml#ConfigureUsersBTF");
        return null;
    }
}
