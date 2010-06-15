/*
* JBoss, Home of Professional Open Source
* Copyright 2009, Red Hat Inc., and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jboss.as.integration.hornetq.jopr;

import org.jboss.as.integration.hornetq.jopr.util.ManagementSupport;
import org.jboss.deployers.spi.management.ManagementView;
import org.jboss.managed.api.ComponentType;
import org.jboss.managed.api.ManagedComponent;
import org.jboss.managed.api.ManagedOperation;
import org.jboss.metatype.api.values.SimpleValueSupport;
import org.rhq.core.pluginapi.inventory.*;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;

/**
 * @author <a href="mailto:andy.taylor@jboss.org">Andy Taylor</a>
 * Created 11-Mar-2010
 */
public class JMSManagerDiscoveryComponent implements ResourceDiscoveryComponent
{
   public Set<DiscoveredResourceDetails> discoverResources(ResourceDiscoveryContext ctx) throws InvalidPluginConfigurationException, Exception
   {
      ManagementView managementView = getProfileService(ctx);
      ManagedComponent component = managementView.getComponent("JMSServerMO", new ComponentType("JMSManage", "ServerManage"));
      if(component != null)
      {
         DiscoveredResourceDetails detail = new DiscoveredResourceDetails(
                  ctx.getResourceType(), // Resource type
                  ctx.getResourceType().getName(), // Resource key
                  ctx.getResourceType().getName(), // Resource name
                  getVersion(ctx), // HornetQ Resource version
                  "The HornetQ JMS provider", // Description
                  ctx.getDefaultPluginConfiguration(), // Plugin config
                  null // Process info from a process scan
         );
         return Collections.singleton(detail);
      }
      return Collections.EMPTY_SET;
   }

   public ManagementView getProfileService(ResourceDiscoveryContext ctx) throws Exception
   {
      ResourceComponent component = ctx.getParentResourceComponent();
      Method m = component.getClass().getMethod("getConnection");
      Object conn = m.invoke(component);
      m = conn.getClass().getMethod("getManagementView");
      return (ManagementView) m.invoke(conn);
   }
   private String getVersion(ResourceDiscoveryContext ctx) throws Exception
   {
      ManagementView managementView = getProfileService(ctx);
      ManagedOperation operation = ManagementSupport.getOperation(managementView, "JMSServerMO", "getVersion", new ComponentType("JMSManage", "ServerManage"));
      SimpleValueSupport support = (SimpleValueSupport) operation.invoke();
      return support.getValue().toString();
   }
}
