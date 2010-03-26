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
package org.jboss.as.integration.hornetq.jopr.util;

import org.jboss.deployers.spi.management.ManagementView;
import org.jboss.managed.api.ComponentType;
import org.jboss.managed.api.ManagedComponent;
import org.jboss.managed.api.ManagedOperation;

import java.util.Set;

/**
 * @author <a href="mailto:andy.taylor@jboss.org">Andy Taylor</a>
 * Created 16-Mar-2010
 */
public class ManagementSupport
{
   public static ManagedOperation getOperation(ManagementView view, String componentName, String operName, ComponentType componentType) throws Exception
   {
      ManagedComponent component = view.getComponent(componentName, componentType);
      Set<ManagedOperation> operations = component.getOperations();
      for (ManagedOperation operation : operations)
      {
         if (operation.getName().equals(operName))
         {
            return operation;
         }
      }
      throw new IllegalArgumentException(" no operation available " + operName);
   }
}
