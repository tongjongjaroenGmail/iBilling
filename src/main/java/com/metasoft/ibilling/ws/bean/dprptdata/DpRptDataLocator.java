/**
 * DpRptDataLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.metasoft.ibilling.ws.bean.dprptdata;


public class DpRptDataLocator extends org.apache.axis.client.Service implements DpRptData {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DpRptDataLocator() {
    }


    public DpRptDataLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DpRptDataLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for dpRptDataPort
    private java.lang.String dpRptDataPort_address = "http://dp.isurvey.mobi/isurvey/service/WS/dpService.php";

    public java.lang.String getdpRptDataPortAddress() {
        return dpRptDataPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String dpRptDataPortWSDDServiceName = "dpRptDataPort";

    public java.lang.String getdpRptDataPortWSDDServiceName() {
        return dpRptDataPortWSDDServiceName;
    }

    public void setdpRptDataPortWSDDServiceName(java.lang.String name) {
        dpRptDataPortWSDDServiceName = name;
    }

    public DpRptDataPortType getdpRptDataPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(dpRptDataPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getdpRptDataPort(endpoint);
    }

    public DpRptDataPortType getdpRptDataPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            DpRptDataBindingStub _stub = new DpRptDataBindingStub(portAddress, this);
            _stub.setPortName(getdpRptDataPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setdpRptDataPortEndpointAddress(java.lang.String address) {
        dpRptDataPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (DpRptDataPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                DpRptDataBindingStub _stub = new DpRptDataBindingStub(new java.net.URL(dpRptDataPort_address), this);
                _stub.setPortName(getdpRptDataPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("dpRptDataPort".equals(inputPortName)) {
            return getdpRptDataPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:dpRptData", "dpRptData");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:dpRptData", "dpRptDataPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("dpRptDataPort".equals(portName)) {
            setdpRptDataPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
