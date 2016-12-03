package com.lab.kot.school.ui.bean;

import com.lab.kot.school.ui.handler.utility.JSFUtil;
import com.lab.kot.school.ui.report.Reports;

import java.io.IOException;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.model.AttributeCriterion;
import oracle.adf.view.rich.model.AttributeDescriptor;
import oracle.adf.view.rich.model.ConjunctionCriterion;
import oracle.adf.view.rich.model.Criterion;
import oracle.adf.view.rich.model.QueryDescriptor;

import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.trinidad.model.UploadedFile;

public class StudentSearchBackBean extends Reports{
    public StudentSearchBackBean() {
        super();
    }

    public void OnQuery(QueryEvent queryEvent) {
        QueryDescriptor qdesc = (QueryDescriptor) queryEvent.getDescriptor();
        ConjunctionCriterion conCrit = qdesc.getConjunctionCriterion();
        //access the list of search fields
        List<Criterion> criterionList = conCrit.getCriterionList();
        boolean selection = false;
        String stId = "";
        String fName = "";
        String lName = "";
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
            if (attrDescriptor.getName().equalsIgnoreCase("SFirstName")) {
                fName = (String) ((AttributeCriterion) criterion).getValues().get(0);

                if (fName != null && !"".equalsIgnoreCase(fName)) {
                    selection = true;
                }
            }
            if (attrDescriptor.getName().equalsIgnoreCase("SLastName")) {
                lName = (String) ((AttributeCriterion) criterion).getValues().get(0);
                if (lName != null && !"".equalsIgnoreCase(lName)) {
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
            JSFUtil.invokeQueryMethodExpression("#{bindings.StudentSearchCriteriaQuery.processQuery}", queryEvent);
        } else {
            JSFUtil.addFacesErrorMessage("Please make a criteria selection");
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.renderResponse();
        }

    }


    public void exportActionListener(ActionEvent actionEvent) {
        try {
                   Map m = new HashMap();
//                   m.put("CbsStatus", cbsStatus);
//                   m.put("BranchId", inputBranchId);
//                   m.put("FromDate", fromDate);
//                   m.put("ToDate", toDate);
                   runReport("Account_Posting.jasper", m);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }               
    }

    public void generateReport(FacesContext facesContext, OutputStream outputStream) {
        // Add event code here...

    }

    public void fileUpload(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
//            UIComponent ui = valueChangeEvent.getComponent();
//            BlobDomain bFile = new BlobDomain();
            UploadedFile fileVal = (UploadedFile) ((List) valueChangeEvent.getNewValue()).get(0);
//            String uploadYN = JSFUtil.uploadFile(fileVal, bFile);
            if (fileVal!=null) {
                DCIteratorBinding dcIt = JSFUtil.getDCIterator("SStudentVO1Iterator");
                Row cRow = dcIt.getCurrentRow();
                cRow.setAttribute("SPhoto", JSFUtil.createBlobDomain(fileVal));
                System.out.println("sphoto");
            } else {
                JSFUtil.addFacesErrorMessage("Uploading Failed");
            }

        }

    }
    public void FileDownloadActionListenerSty(FacesContext facesContext, OutputStream outputStream) {        
        BlobDomain bFile = (BlobDomain)JSFUtil.resolveExpression("#{bindings.SPhoto.inputValue}");
        try
        {   // copy hte data from the BlobDomain to the output stream 
            IOUtils.copy(bFile.getInputStream(), outputStream);                
            bFile.closeInputStream();                
            outputStream.flush();                
        }
        catch (IOException e)
        {
        }
//        JSFUtil.downloadFile(bFile,outputStream);                   
    }


    public void editActionListener(ActionEvent actionEvent) {
        String dcFrameName =
                   BindingContext.getCurrent().getCurrentDataControlFrame();
               

               //Store that on the session:
               FacesContext ctx = FacesContext.getCurrentInstance();
               HttpSession session =
                   (HttpSession)ctx.getExternalContext().getSession(false);
               session.setAttribute("DC_FRAME_NAME", dcFrameName);
//        JSFUtil.invokeEL("#{bindings.setCurrentRowWithKey.execute}");
    }
}
