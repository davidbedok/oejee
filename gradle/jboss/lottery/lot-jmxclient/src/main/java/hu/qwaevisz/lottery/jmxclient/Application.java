package hu.qwaevisz.lottery.jmxclient;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Application {

	private static final String HOST = "localhost";
	private static final int MANAGEMENT_NATIVE_PORT = 9999;

	private static final String OBJECT_GROUP = "lottery.mbean";
	private static final String MBEAN_SERVICE = "LotteryMonitorMBean";

	public static void main(String[] args) {
		try {
			JMXServiceURL serviceURL = new JMXServiceURL(buildServiceUrl());
			JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
			MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();

			ObjectName name = new ObjectName(buildObjectName(OBJECT_GROUP, MBEAN_SERVICE));
			System.out.println("Puller name: " + connection.getAttribute(name, "Puller").toString());
			System.out.println("Prize pool: " + connection.getAttribute(name, "PrizePool").toString());

			for (int i = 1; i <= 5; i++) {
				Object operationResult = connection.invoke(name, "getDistribution", new Object[] { i }, new String[] { int.class.getName() });
				System.out.println("Distribution " + i + ": " + operationResult.toString());
			}

			jmxConnector.close();
		} catch (IOException | MalformedObjectNameException | InstanceNotFoundException | MBeanException | ReflectionException | AttributeNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String buildServiceUrl() {
		return "service:jmx:remoting-jmx://" + HOST + ":" + MANAGEMENT_NATIVE_PORT;
	}

	private static String buildObjectName(String group, String service) {
		return group + ":service=" + service;
	}

}
