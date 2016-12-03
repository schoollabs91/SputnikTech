package com.lab.kot.school.ui.bean;

import com.lab.kot.school.ui.handler.utility.JSFUtil;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.event.QueryEvent;

import oracle.adf.view.rich.model.AttributeCriterion;
import oracle.adf.view.rich.model.AttributeDescriptor;
import oracle.adf.view.rich.model.ConjunctionCriterion;
import oracle.adf.view.rich.model.Criterion;
import oracle.adf.view.rich.model.QueryDescriptor;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;

public class StudentAttendanceSearchBean {
    public StudentAttendanceSearchBean() {
        super();
    }

    public void createAttendanceList(ActionEvent actionEvent) {
//       #{bindings.copyStudentToAttendance.execute}
        
        BindingContainer bindings =(BindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry(); 
        OperationBinding method = bindings.getOperationBinding("copyStudentToAttendance");  
        Object result = method.execute(); 
        
    }

    public void OnQuery(QueryEvent queryEvent) {
        QueryDescriptor qdesc = (QueryDescriptor) queryEvent.getDescriptor();
        ConjunctionCriterion conCrit = qdesc.getConjunctionCriterion();
        //access the list of search fields
        List<Criterion> criterionList = conCrit.getCriterionList();
        boolean selection = false;
        String stId = "";
        Integer month = null;        
        Integer cCode = null;
        Integer secCode = null;
        Integer sessCode = null;

        for (Criterion criterion : criterionList) {
            AttributeDescriptor attrDescriptor = ((AttributeCriterion) criterion).getAttribute();
            if (attrDescriptor.getName().equalsIgnoreCase("SId")) {
                stId = (String) ((AttributeCriterion) criterion).getValues().get(0);

                if (stId != null && !"".equalsIgnoreCase(stId)) {
                    selection = true;
                }
            }
            if (attrDescriptor.getName().equalsIgnoreCase("Month")) {
                month = (Integer) ((AttributeCriterion) criterion).getValues().get(0);

                if (month != null) {
                    selection = true;
                }
            }
          
            if (attrDescriptor.getName().equalsIgnoreCase("ClassCode")) {
                cCode = (Integer) ((AttributeCriterion) criterion).getValues().get(0);
                if (cCode != null) {
                    selection = true;
                }
            }
            if (attrDescriptor.getName().equalsIgnoreCase("SectionCode")) {
                System.out.println(((AttributeCriterion) criterion).getValues().get(0));
                secCode = (Integer) ((AttributeCriterion) criterion).getValues().get(0);
                if (secCode != null ) {
                    selection = true;
                }
            }
            if (attrDescriptor.getName().equalsIgnoreCase("SessionCode")) {
                sessCode = (Integer) ((AttributeCriterion) criterion).getValues().get(0);
                if (sessCode != null ) {
                    selection = true;
                }
            }

        }
        if (selection) {
            JSFUtil.invokeQueryMethodExpression("#{bindings.SStudAttendSearchCriteriaQuery.processQuery}", queryEvent);
        } else {
            JSFUtil.addFacesErrorMessage("Please make a criteria selection");
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.renderResponse();
        }

    }
}
