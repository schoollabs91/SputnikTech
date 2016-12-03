package com.lab.kot.school.ui.report;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.sql.Connection;

import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

import oracle.adf.model.BindingContext;

import oracle.binding.BindingContainer;


public class Reports implements Serializable{
    public Reports() {
        super();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public Connection getDataSourceConnection(String dataSourceName) throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup(dataSourceName);
        return ds.getConnection();
    }

    private Connection getConnection() throws Exception {
        return getDataSourceConnection("IFAMDBDS");
    }

    public ServletContext getContext() {
        return (ServletContext) getFacesContext().getExternalContext().getContext();
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     *
     * @param repPath - Jasper file name is passed
     * @param param - parameters used to generate the report are passed in the form of Map
     * @throws Exception
     * Description: Generate the report for the file name and parameters passed and output will be parsed as a PDF
     */
    public void runReport(String repPath, java.util.Map param) throws Exception {
        Connection conn = null;
        try {
            HttpServletResponse response = getResponse();
            ServletOutputStream out = response.getOutputStream();
            response.setHeader("Cache-Control", "max-age=0");
            response.setContentType("application/pdf");
            ServletContext context = getContext();
            InputStream fs = context.getResourceAsStream("/reports/" + repPath);
            //            String image =
            //                FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reports/images/logo.jpg");
            InputStream image = context.getResourceAsStream("/reports/images/logo.jpg");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fs);
            jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
            conn = getConnection();
            param.put("SBILOGO", image);
            JasperPrint jasperPrint;
            jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            out.write(baos.toByteArray());
            out.flush();
            out.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception jex) {
            jex.printStackTrace();
        } finally {
            close(conn);
        }
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
            }
        }
    }

}
