/*
 * Copyright 2009 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.jboss.as.integration.hornetq.jopr;

import org.jboss.as.integration.hornetq.jopr.util.ManagementSupport;
import org.jboss.deployers.spi.management.ManagementView;
import org.jboss.managed.api.ComponentType;
import org.jboss.managed.api.ManagedOperation;
import org.jboss.metatype.api.types.SimpleMetaType;
import org.jboss.metatype.api.values.SimpleValueSupport;
import org.rhq.core.domain.configuration.ConfigurationUpdateStatus;
import org.rhq.core.domain.configuration.PropertySimple;
import org.rhq.core.pluginapi.configuration.ConfigurationUpdateReport;

import java.util.Map;

import static org.jboss.as.integration.hornetq.jopr.JMSConstants.ConnectionFactory.COMPONENT_NAME;
import static org.jboss.as.integration.hornetq.jopr.JMSConstants.ConnectionFactory.COMPONENT_TYPE;

/**
 * @author <a href="mailto:andy.taylor@jboss.org">Andy Taylor</a>
 *         Created Mar 19, 2010
 */
public class JMSConnectionFactoryComponent extends JMSResourceComponent
{

   @Override
   protected String getInvokeOperationSubscriptionMessage()
   {
      return null;
   }

   @Override
   protected String getInvokeOperationJMSMessage()
   {
      return null;
   }

   @Override
   protected String getInvokeOperation()
   {
      return "";
   }

   @Override
   String getComponentName()
   {
      return COMPONENT_NAME;
   }

   @Override
   ComponentType getComponentType()
   {
      return COMPONENT_TYPE;
   }

   @Override
   String getConfigurationOperationName()
   {
      return "getConfiguration";
   }

   @Override
   String getMeasurementsOperationName()
   {
      return "getMeasurements";
   }

   @Override
   String getDeleteOperationName()
   {
      return "deleteConnectionFactory";
   }

   public void updateResourceConfiguration(ConfigurationUpdateReport configurationUpdateReport)
   {
      Map<String, PropertySimple> simpleProps = configurationUpdateReport.getConfiguration().getSimpleProperties();
      String name = simpleProps.get("name").getStringValue();
      String clientId = simpleProps.get("ClientID").getStringValue();
      int dupsOkBatchSize = simpleProps.get("DupsOKBatchSize").getIntegerValue();
      int transactionBatchSize = simpleProps.get("TransactionBatchSize").getIntegerValue();
      long clientFailureCheckPeriod = simpleProps.get("ClientFailureCheckPeriod").getLongValue();
      long connectionTTL = simpleProps.get("ConnectionTTL").getLongValue();
      long callTimeout = simpleProps.get("CallTimeout").getLongValue();
      int consumerWindowSize = simpleProps.get("ConsumerWindowSize").getIntegerValue();
      int confirmationWindowSize = simpleProps.get("ConfirmationWindowSize").getIntegerValue();
      int producerMaxRate = simpleProps.get("ProducerMaxRate").getIntegerValue();
      int producerWindowSize = simpleProps.get("ProducerWindowSize").getIntegerValue();
      boolean cacheLargeMessageClient = simpleProps.get("CacheLargeMessagesClient").getBooleanValue();
      int minLargeMessageSize = simpleProps.get("MinLargeMessageSize").getIntegerValue();
      boolean blockOnNonDurableSend = simpleProps.get("BlockOnNonDurableSend").getBooleanValue();
      boolean blockOnAcknowledge = simpleProps.get("BlockOnAcknowledge").getBooleanValue();
      boolean blockOnDurableSend = simpleProps.get("BlockOnDurableSend").getBooleanValue();
      boolean autoGroup = simpleProps.get("AutoGroup").getBooleanValue();
      boolean preAcknowledge = simpleProps.get("PreAcknowledge").getBooleanValue();
      long maxRetryInterval = simpleProps.get("MaxRetryInterval").getLongValue();
      double retryIntervalMultiplier = simpleProps.get("RetryIntervalMultiplier").getDoubleValue();
      int reconnectAttempts = simpleProps.get("ReconnectAttempts").getIntegerValue();
      int scheduledThreadPoolMaxSize = simpleProps.get("ScheduledThreadPoolMaxSize").getIntegerValue();
      int threadPoolMaxSize = simpleProps.get("ThreadPoolMaxSize").getIntegerValue();
      String groupId = simpleProps.get("GroupID").getStringValue();
      int initialMessagePacketSize = simpleProps.get("InitialMessagePacketSize").getIntegerValue();
      boolean useGlobalPools = simpleProps.get("UseGlobalPools").getBooleanValue();
      long retryInterval = simpleProps.get("RetryInterval").getLongValue();
      String connectionLoadBalancingPolicyClassName = simpleProps.get("ConnectionLoadBalancingPolicyClassName").getStringValue();

      try
      {
         ManagementView managementView = getProfileService();

         updateConnectionFactory(configurationUpdateReport, managementView, name, clientId, dupsOkBatchSize, transactionBatchSize, clientFailureCheckPeriod, connectionTTL, callTimeout, consumerWindowSize, confirmationWindowSize, producerMaxRate, producerWindowSize, cacheLargeMessageClient, minLargeMessageSize, blockOnNonDurableSend, blockOnAcknowledge, blockOnDurableSend, autoGroup, preAcknowledge, maxRetryInterval, retryIntervalMultiplier, reconnectAttempts, scheduledThreadPoolMaxSize, threadPoolMaxSize, groupId, initialMessagePacketSize, useGlobalPools, retryInterval, connectionLoadBalancingPolicyClassName);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         configurationUpdateReport.setStatus(ConfigurationUpdateStatus.FAILURE);
         configurationUpdateReport.setErrorMessage(e.getMessage());
      }

   }

   private void updateConnectionFactory(ConfigurationUpdateReport configurationUpdateReport,
                                        ManagementView managementView,
                                        String name,
                                        String clientId,
                                        int dupsOkBatchSize,
                                        int transactionBatchSize,
                                        long clientFailureCheckPeriod,
                                        long connectionTTL,
                                        long callTimeout,
                                        int consumerWindowSize,
                                        int confirmationWindowSize,
                                        int producerMaxRate,
                                        int producerWindowSize,
                                        boolean cacheLargeMessageClient,
                                        int minLargeMessageSize,
                                        boolean blockOnNonDurableSend,
                                        boolean blockOnAcknowledge,
                                        boolean blockOnDurableSend,
                                        boolean autoGroup,
                                        boolean preAcknowledge,
                                        long maxRetryInterval,
                                        double retryIntervalMultiplier,
                                        int reconnectAttempts,
                                        int scheduledThreadPoolMaxSize,
                                        int threadPoolMaxSize,
                                        String groupId,
                                        int initialMessagePacketSize,
                                        boolean useGlobalPools,
                                        long retryInterval,
                                        String connectionLoadBalancingPolicyClassName) throws Exception
   {
      ManagedOperation operation = ManagementSupport.getOperation(managementView, COMPONENT_NAME, "updateConnectionFactory", new ComponentType("JMSManage", "ConnectionFactoryManage"));
      operation.invoke(new SimpleValueSupport(SimpleMetaType.STRING, name),
            new SimpleValueSupport(SimpleMetaType.STRING, clientId),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, dupsOkBatchSize),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, transactionBatchSize),
            new SimpleValueSupport(SimpleMetaType.LONG_PRIMITIVE, clientFailureCheckPeriod),
            new SimpleValueSupport(SimpleMetaType.LONG_PRIMITIVE, connectionTTL),
            new SimpleValueSupport(SimpleMetaType.LONG_PRIMITIVE, callTimeout),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, consumerWindowSize),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, confirmationWindowSize),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, producerMaxRate),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, producerWindowSize),
            new SimpleValueSupport(SimpleMetaType.BOOLEAN_PRIMITIVE, cacheLargeMessageClient),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, minLargeMessageSize),
            new SimpleValueSupport(SimpleMetaType.BOOLEAN_PRIMITIVE, blockOnNonDurableSend),
            new SimpleValueSupport(SimpleMetaType.BOOLEAN_PRIMITIVE, blockOnAcknowledge),
            new SimpleValueSupport(SimpleMetaType.BOOLEAN_PRIMITIVE, blockOnDurableSend),
            new SimpleValueSupport(SimpleMetaType.BOOLEAN_PRIMITIVE, autoGroup),
            new SimpleValueSupport(SimpleMetaType.BOOLEAN_PRIMITIVE, preAcknowledge),
            new SimpleValueSupport(SimpleMetaType.LONG_PRIMITIVE, maxRetryInterval),
            new SimpleValueSupport(SimpleMetaType.DOUBLE_PRIMITIVE, retryIntervalMultiplier),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, reconnectAttempts),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, scheduledThreadPoolMaxSize),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, threadPoolMaxSize),
            new SimpleValueSupport(SimpleMetaType.STRING, groupId),
            new SimpleValueSupport(SimpleMetaType.INTEGER_PRIMITIVE, initialMessagePacketSize),
            new SimpleValueSupport(SimpleMetaType.BOOLEAN_PRIMITIVE, useGlobalPools),
            new SimpleValueSupport(SimpleMetaType.LONG_PRIMITIVE, retryInterval),
            new SimpleValueSupport(SimpleMetaType.STRING, connectionLoadBalancingPolicyClassName));

      configurationUpdateReport.setStatus(ConfigurationUpdateStatus.SUCCESS);
   }
}
