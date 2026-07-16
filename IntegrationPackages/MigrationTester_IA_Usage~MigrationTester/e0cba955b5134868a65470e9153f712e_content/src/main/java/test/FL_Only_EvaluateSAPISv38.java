package test;

import com.sap.aii.mapping.api.*;
import com.sap.aii.mapping.lookup.*;
import com.sap.aii.mappingtool.tf7.rt.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import com.sap.aii.mappingtool.functionlibrary.*;

public class FL_Only_EvaluateSAPISv38 extends AFunctionLibrary {

    final String cDebugClass = "[MM_EvaluateSAPISv38_Generic]";

    //!========================================================================================================================================0
    //!=  Main Class
    //!========================================================================================================================================0
    final String cCodex = "[MM38:1138]";

    //!======================================================================================================================================
    //!=  Perform Hello-World
    //!======================================================================================================================================
    public String EXEcHelloWorld(final String cDebugUpper, final String cValue) {
        final String cDebug = cDebugUpper + cCodex + "[EXEcHelloWorld]";
        return (String) cDebug + cDebugClass + cValue;
    }

    //!======================================================================================================================================
    //!=  Perform Validations
    //!======================================================================================================================================
    public String EXEcValidation(final String cDebugUpper, final String cParameter) {
        final String cDebug = cDebugUpper + cCodex + "[EXEcValidation]";
        String cReturn = "";
        if (cParameter.equals("!HIWORLD")) {
            // validate presence of Java-Classs ===========================================================
            cReturn = "Hello World";
        } else if (cParameter.equals("!DOMCHECK")) {
            // check presence of Java-Classs ======================================================
            cReturn = "Relevant Transform-Code is not executed.";
            final ClassLoader oClassLoaderOld = Thread.currentThread().getContextClassLoader();
            try {
                final DocumentBuilderFactory oDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
                final DocumentBuilder oDocumentBuilder = oDocumentBuilderFactory.newDocumentBuilder();
                final Document oDocument = oDocumentBuilder.parse(new InputSource(new StringReader("<RootXML><InnerTag>HelloWorld</InnerTag></RootXML>")));
                // origin pretty printing
                final StringWriter oStringWriter = new StringWriter();
                Thread.currentThread().setContextClassLoader(javax.xml.transform.TransformerFactory.class.getClassLoader());
                // full qualification for SAPCI needed
                javax.xml.transform.Transformer oTransformer = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
                oTransformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                oTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
                oTransformer.transform(new DOMSource(oDocument), new StreamResult(oStringWriter));
                final String cOutput1 = oStringWriter.toString();
                final String cExpected1 = "<RootXML>\n<InnerTag>HelloWorld</InnerTag>\n</RootXML>\n";
                if (cOutput1.equals("")) {
                    cReturn = "cOutput1 is empty; perhaps transform function is commented/removed";
                } else if (!cOutput1.equals(cExpected1)) {
                    // UNIX: only "\n" (= without "\r")
                    cReturn = "cOutput1 is different (nCurrent/nExpected): " + Long.toString(cOutput1.length()) + "/" + Long.toString(cExpected1.length());
                } else {
                    cReturn = "cOutput1 is as expected (nCurrent): " + Long.toString(cOutput1.length());
                }
            } catch (javax.xml.transform.TransformerException oTransformerException) {
                // full qualification for SAPCI needed
                // cleanup of oClassLoaderOld is done in finally
                cReturn = "oTransformerException: " + oTransformerException.toString();
            } catch (Exception oException) {
                // cleanup of oClassLoaderOld is done in finally
                cReturn = "oException: " + oException.toString();
            } catch (Throwable oThrowable) {
                // cleanup of oClassLoaderOld is done in finally
                cReturn = "oThrowable: " + oThrowable.toString();
            } finally {
                Thread.currentThread().setContextClassLoader(oClassLoaderOld);
            }
        } else {
            // check of Java-Classs =====================================================================================================
            final String cJavaClassName = cParameter;
            try {
                Class.forName(cJavaClassName);
                cReturn = "Java-Class " + cJavaClassName + ": Yes, class exists";
            } catch (ClassNotFoundException oExceptionUnused) {
                cReturn = "Java-Class " + cJavaClassName + ": No, class does not exist";
            } catch (Exception oException) {
                cReturn = "oException: " + oException.toString();
            }
        }
        // IF-THEN-ELSEIF-END for cParameter
        return (String) cDebug + cReturn;
    }

    // method EXEcValidation
    //!========================================================================================================================================0
    @Init
    public void init(GlobalContainer container) throws StreamTransformationException {
    }

    @CleanUp
    public void cleanUp(GlobalContainer container) throws StreamTransformationException {
    }

    //The key property of the UDFs should not be changed. If it is changed it cannot be used
    //in the Message Mappings which are imported from ESR system.
    @FunctionLibraryMethod(category = "FL_Only_EvaluateSAPISv38", title = "UDF_MM_General_fromFL", executionType = "SINGLE_VALUE", key = "b986733d-26af-11f1-a0fa-020100d5c96a")
    public String UDF_MM_General_fromFL(@UDFParam(paramCategory = "Argument", title = "") String cParameter, Container container) throws StreamTransformationException {
        final String cDebug = cCodex + "[UDF_MM_General_fromFL]";
        String cReturn = "";
        if (cParameter.equals("")) {
            cReturn = "(empty-parameter)";
            return (String) cDebug + "cReturn: " + cReturn;
        }
        try {
            cReturn = "Step-22: Trying to instantiate Java-Class.";
            // EvaluateSAPISv38_IA_ImportedArchive oEvaluateSAPISv38 = new EvaluateSAPISv38_IA_ImportedArchive();
            cReturn = "Step-24: Perform EXEcHelloWorld().";
            // oEvaluateSAPISv38.
            cReturn = EXEcHelloWorld(cDebug, cParameter);
            if (cParameter.equals("X") || cParameter.startsWith("X-")) {
                // oEvaluateSAPISv38 = null;
                return (String) cDebug + "cReturn: " + cReturn;
            }
            cReturn = "Step-26: Perform EXEcValidation().";
            // oEvaluateSAPISv38.
            cReturn = EXEcValidation(cDebug, cParameter);
            // oEvaluateSAPISv38 = null;
        } catch (Exception oException) {
            cReturn = "oException: " + cReturn + " // " + oException.toString();
            return (String) cDebug + "cReturn: " + cReturn;
        } catch (Throwable oThrowable) {
            cReturn = "oThrowable: " + cReturn + " // " + oThrowable.toString();
            return (String) cDebug + "cReturn: " + cReturn;
        }
        return (String) cDebug + "cReturn: " + cReturn;
    }
}
