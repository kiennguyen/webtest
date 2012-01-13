/**
 * Copyright (C) 2012 eXo Platform SAS.
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
package org.webtest.example;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.resource.Resource;

import java.net.URL;

import junit.framework.TestCase;

/**
 * @author <a href="kienna@exoplatform.com">Kien Nguyen</a>
 * @version $Revision$
 */
public class JettyTest extends TestCase
{
   private static final int TEST_PORT = 9876;
   private static final String RESOURCES_PATH = "/jettypages";
   private static final String SERVER_URL = "http://localhost:" + TEST_PORT;
   
   private Server server;   
   
   @Override
   public void setUp() throws Exception
   {
      server = createServer(TEST_PORT);
      server.start();
   }

   @Override
   public void tearDown() throws Exception
   {
      server.stop();
   }
   
   private Server createServer(int port) throws Exception
   {
      Server jetty = new Server(port);
      
      //Attach static resources for server
      ResourceHandler resources = new ResourceHandler();
      URL url = JettyTest.class.getResource(RESOURCES_PATH);
      resources.setBaseResource(Resource.newResource(url));
      jetty.addHandler(resources);
      
      return jetty;
   }
   
   public void testIndexHtmlPage() throws Exception {
      final WebClient webClient = new WebClient();
      final HtmlPage page = webClient.getPage(SERVER_URL);
      assertEquals("Jetty Test", page.getTitleText());

      webClient.closeAllWindows();
  }
}
