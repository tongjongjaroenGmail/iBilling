/**
 * DpRptDataPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.metasoft.ibilling.ws.bean.dprptdata;

public interface DpRptDataPortType extends java.rmi.Remote {

    /**
     * get claim status report
     */
    public java.lang.String getRptData(java.lang.String user, java.lang.String pass, java.lang.String rptDate) throws java.rmi.RemoteException;

    /**
     * update receive flag
     */
    public java.lang.String confirmReceiveRpt(java.lang.String user, java.lang.String pass, java.lang.String rptID, java.lang.String claim_no) throws java.rmi.RemoteException;
}
