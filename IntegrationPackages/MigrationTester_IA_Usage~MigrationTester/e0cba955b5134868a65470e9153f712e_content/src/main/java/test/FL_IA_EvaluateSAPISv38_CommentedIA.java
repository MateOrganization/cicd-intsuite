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
import com.pg.xi.tom.edi.dmtv02.*;
import com.sap.aii.mappingtool.functionlibrary.*;

public class FL_IA_EvaluateSAPISv38_CommentedIA extends AFunctionLibrary {

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

    @Init
    public void init(GlobalContainer container) throws StreamTransformationException {
    }

    @CleanUp
    public void cleanUp(GlobalContainer container) throws StreamTransformationException {
    }

    //The key property of the UDFs should not be changed. If it is changed it cannot be used
    //in the Message Mappings which are imported from ESR system.
    @FunctionLibraryMethod(category = "FL_IA_EvaluateSAPISv38_CommentedIA", title = "UDF_FromFLviaIA_CommentedIA", executionType = "SINGLE_VALUE", key = "254a122b-2692-11f1-824e-020100d5c96a")
    public String UDF_FromFLviaIA_CommentedIA(@UDFParam(paramCategory = "Argument", title = "") String cParameter, Container container) throws StreamTransformationException {
        final String cDebug = cCodex + "[UDF_FromFLviaIA_CommentedIA]";
        String cReturn = "";
        if (cParameter.equals("")) {
            cReturn = "(empty-parameter)";
            return (String) cDebug + "cReturn: " + cReturn;
        }
        try {
            cReturn = "Step-22: Trying to instantiate Java-Class.";
            EvaluateSAPISv38_IA_ImportedArchive_Commented oEvaluateSAPISv38 = new EvaluateSAPISv38_IA_ImportedArchive_Commented();
            cReturn = "Step-24: Perform EXEcHelloWorld().";
            cReturn = oEvaluateSAPISv38.EXEcHelloWorld(cDebug, cParameter);
            if (cParameter.equals("X") || cParameter.startsWith("X-")) {
                oEvaluateSAPISv38 = null;
                return (String) cDebug + "cReturn: " + cReturn;
            }
            cReturn = "Step-26: Perform EXEcValidation().";
            cReturn = oEvaluateSAPISv38.EXEcValidation(cDebug, cParameter);
            oEvaluateSAPISv38 = null;
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
