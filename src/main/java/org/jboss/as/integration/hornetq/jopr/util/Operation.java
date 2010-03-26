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

/**
 * @author <a href="mailto:andy.taylor@jboss.org">Andy Taylor</a>
 *         Created Feb 9, 2010
 */
public class Operation
{
   final private String operationName;
      final private String resultsType;

      Operation(String operationName, String resultsType)
      {
         this.operationName = operationName;
         this.resultsType = resultsType;
      }

      public String getOperationName()
      {
         return operationName;
      }

      public String getResultsType()
      {
         return resultsType;
      }

      public static Operation getOperation(String oper)
      {
         String[] split = oper.split(",", 3);
         String operationName = split[0];
         String resultsType = null;
         for (int i = 1, splitLength = split.length; i < splitLength; i++)
         {
            String s = split[i];
            if(s.startsWith("operation"))
            {
               operationName = s.substring(s.indexOf("=") + 1);
            }
            else if(s.startsWith("result"))
            {
               resultsType = s.substring(s.indexOf("=") + 1);
            }
         }
         return new Operation(operationName, resultsType);
      }
}
