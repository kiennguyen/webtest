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

import com.gargoylesoftware.htmlunit.BrowserVersion;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import com.gargoylesoftware.htmlunit.WebClient;

import junit.framework.TestCase;

/**
 * @author <a href="kienna@exoplatform.com">Kien Nguyen</a>
 * @version $Revision$
 */
public class HtmlUnitTest extends TestCase
{
   @Override
   public void setUp() throws Exception
   {

   }

   @Override
   public void tearDown() throws Exception
   {

   }

   public void testHomePage() throws Exception
   {
      final WebClient webClient = new WebClient();
      final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
      assertEquals("Welcome to HtmlUnit", page.getTitleText());

      final String pageAsXml = page.asXml();
      assertTrue(pageAsXml.contains("<body class=\"composite\">"));

      final String pageAsText = page.asText();
      assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));

      webClient.closeAllWindows();
   }
   
   public void testHomePage_Firefox() throws Exception {
       final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
       final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
       assertEquals("Welcome to HtmlUnit", page.getTitleText());

       webClient.closeAllWindows();
   }
}
