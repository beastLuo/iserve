/*
   Copyright ${year}  Knowledge Media Institute - The Open University

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package uk.ac.open.kmi.iserve.sal.gwt.server.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.wsdl.WSDLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.ontoware.rdf2go.model.Syntax;
import org.openrdf.repository.RepositoryException;

import uk.ac.open.kmi.iserve.sal.exception.ServiceException;
import uk.ac.open.kmi.iserve.sal.gwt.server.Factory;
import uk.ac.open.kmi.iserve.sal.manager.ServiceManager;

public class DownloadFileServlet extends HttpServlet {

	private static final long serialVersionUID = -1509060391445229084L;

	private ServiceManager serviceManager;

	public void init() throws ServletException {
		super.init();
		try {
			Factory facotry = Factory.getInstance();
			serviceManager = facotry.createServiceManager();
		} catch (RepositoryException e) {
			throw new ServletException(e);
		} catch (TransformerConfigurationException e) {
			throw new ServletException(e);
		} catch (WSDLException e) {
			throw new ServletException(e);
		} catch (ParserConfigurationException e) {
			throw new ServletException(e);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String format = request.getParameter("format");
		String uri = request.getParameter("uri");

		String content = "";
		try {
			if ( format.equalsIgnoreCase("xml") ) {
				content = serviceManager.getService(uri, Syntax.RdfXml);
			} else if ( format.equalsIgnoreCase("nt") ) {
				content = serviceManager.getService(uri, Syntax.Ntriples);
			} else if ( format.equalsIgnoreCase("trig") ) {
				content = serviceManager.getService(uri, Syntax.Trig);
			} else if ( format.equalsIgnoreCase("trix") ) {
				content = serviceManager.getService(uri, Syntax.Trix);
			} else if ( format.equalsIgnoreCase("ttl") ) {
				content = serviceManager.getService(uri, Syntax.Turtle);
			}
		} catch (ServiceException e) {
			throw new ServletException(e);
		}

		// write to response.
		ServletOutputStream op = response.getOutputStream();
		response.setContentType("application/octet-stream");
		//response.setContentType("text/plain");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "must-revalidate");
		response.setHeader("Content-Disposition", "Attachment;Filename=\"" + "export." + format.toLowerCase() + "\"");
		op.write(content.getBytes(), 0, content.getBytes().length);
		op.flush();
		op.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String suf = request.getParameter("format");
		String uri = request.getParameter("uri");
		String format = suf;
		String content = "";
		try {
			if ( format.equalsIgnoreCase("xml") ) {
				content = serviceManager.getService(uri, Syntax.RdfXml);
			} else if ( format.equalsIgnoreCase("nt") ) {
				content = serviceManager.getService(uri, Syntax.Ntriples);
			} else if ( format.equalsIgnoreCase("trig") ) {
				content = serviceManager.getService(uri, Syntax.Trig);
			} else if ( format.equalsIgnoreCase("trix") ) {
				content = serviceManager.getService(uri, Syntax.Trix);
			} else if ( format.equalsIgnoreCase("ttl") ) {
				content = serviceManager.getService(uri, Syntax.Turtle);
			}
		} catch (ServiceException e) {
			throw new ServletException(e);
		}

		// write to response.
		ServletOutputStream op = response.getOutputStream();
		response.setContentType("application/octet-stream");
		//response.setContentType("text/plain");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "must-revalidate");
		response.setHeader("Content-Disposition", "Attachment;Filename=\"" + "export." + suf.toLowerCase() + "\"");
		op.write(content.getBytes(), 0, content.getBytes().length);
		op.flush();
		op.close();
	}

}
