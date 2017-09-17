package hu.qwaevisz.lottery.ejbservice.management;

import weblogic.application.ApplicationException;
import weblogic.application.ApplicationLifecycleEvent;
import weblogic.application.ApplicationLifecycleListener;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationMBeanLifeCycleListener extends ApplicationLifecycleListener {

    private static final Logger LOGGER = Logger.getLogger(ApplicationMBeanLifeCycleListener.class.getName());

    private static final String MBEAN_SERVER_JNDI = "java:comp/jmx/runtime";
    private static final String OBJECT_PACKAGE = "hu.qwaevisz.lottery.ejbservice.management";

    public void postStart(ApplicationLifecycleEvent evt) throws ApplicationException {
        LOGGER.info("MBean registration started...");
        try {
            InitialContext context = new InitialContext();
            MBeanServer mbeanServer = MBeanServer.class.cast( context.lookup(MBEAN_SERVER_JNDI) );
            LotteryMonitor mbean = new LotteryMonitor();
            ObjectName oname = this.buildObjectName();
            mbeanServer.registerMBean(mbean, oname);
            LOGGER.info("Registration of mbean is successful ("+oname.getCanonicalName()+").");
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private ObjectName buildObjectName() throws MalformedObjectNameException {
        return new ObjectName(OBJECT_PACKAGE + ":type="+LotteryMonitor.class.getSimpleName()+",name="+LotteryMonitor.class.getSimpleName());
    }

    public void preStop(ApplicationLifecycleEvent evt) throws ApplicationException {
        LOGGER.info("MBean unregistration started...");
        try {
            InitialContext context = new InitialContext();
            MBeanServer mbeanServer = MBeanServer.class.cast( context.lookup(MBEAN_SERVER_JNDI) );
            ObjectName oname = this.buildObjectName();
            if ( mbeanServer.isRegistered(oname) ) {
                mbeanServer.unregisterMBean(oname);
                LOGGER.info("Unregistration of mbean is successful ("+oname.getCanonicalName()+").");
            }
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
