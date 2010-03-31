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

import org.jboss.managed.api.ComponentType;

/**
 * @author <a href="mailto:andy.taylor@jboss.org">Andy Taylor</a>
 *         Created Mar 24, 2010
 */
public class JMSConstants
{
   interface ConnectionFactory
   {
      public static final String COMPONENT_NAME = "JMSConnectionFactoryManageMO";

      public static final ComponentType COMPONENT_TYPE = new ComponentType("JMSManage", "ConnectionFactoryManage");
   }

   interface Queue
   {
      public static final String COMPONENT_NAME = "JMSQueueManageMO";

      public static final ComponentType COMPONENT_TYPE = new ComponentType("JMSDestinationManage", "QueueManage");
   }

   interface Topic
   {
      public static final String COMPONENT_NAME = "JMSTopicManageMO";

      public static final ComponentType COMPONENT_TYPE = new ComponentType("JMSDestinationManage", "TopicManage");
   }

   interface Manager
   {
      public static final String COMPONENT_NAME = "JMSServerMO";
      public static final ComponentType COMPONENT_TYPE = new ComponentType("JMSManage", "ServerManage");
   }
}
