package com.lab.kot.school.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControlFrame;

import oracle.binding.DataControl;

public class ViewImageServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getParameter("path"));


//        OutputStream os = response.getOutputStream();
        //If path is null or file is not an image
//        if (path.equalsIgnoreCase("No")) {
//            path = "D:\\ADF\\NoImage.png";
//        }
//        if (request.getParameter("path") == "") {
//            path = "D:\\ADF\\NoImage.png";
//        }
        
//        BindingContext bindingContext=null;
//        bindingContext = BindingContext.getCurrent();
//        DCBindingContainer dcBindingContainer = bindingContext.findBindingContainer("com_lab_kot_school_ui_StudentAddEditPagePageDef");
//        dcBindingContainer.getAttributeBindings().        
//        OperationBinding operation = (OperationBinding)dcBindingContainer.getOperationBinding("MethodName");
        System.out.println("11111");
        
        HttpSession session = request.getSession();
        String dcFrameName = (String)session.getAttribute("DC_FRAME_NAME");
                        BindingContext bctx = BindingContext.getCurrent();
        DataControlFrame dcframe = bctx.findDataControlFrame(dcFrameName);
        DataControl dc = dcframe.findDataControl("SchoolSystemAMDataControl");
        
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        String outputFromPojo = "sample date ";
        out.println("<html>");
        out.println("<body>");
        out.println("<h2>Output from Servlet</h2>");
        out.println("<p>" + outputFromPojo + "</p>");
        out.println("</body></html>");
        out.close();
        
     /*   
        DCIteratorBinding dcIt = JSFUtil.getDCIterator("SStudentVO1Iterator");
        Row cRow = dcIt.getCurrentRow();
        BlobDomain bFile = (BlobDomain)cRow.getAttribute("SPhoto");
        System.out.println("22222");
        InputStream inputStream = null;

        try {     
            System.out.println("333");
            IOUtils.copy(bFile.getInputStream(), os);  
            System.out.println("44444");
        } catch (Exception e) {

            System.out.println(e);
        } finally {
            if (os != null) {
                os.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }

        }
*/
    }

}
