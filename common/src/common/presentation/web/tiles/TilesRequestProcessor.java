package common.presentation.web.tiles;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.InvalidCancelException;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.tiles.DefinitionsFactory;
import org.apache.struts.util.MessageResources;


public class TilesRequestProcessor extends org.apache.struts.tiles.TilesRequestProcessor {

	private static final Log logger = LogFactory.getLog(TilesRequestProcessor.class);

	@Override
	protected void doForward(String uri, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.doForward(uri, request, response);
	}

	@Override
	public DefinitionsFactory getDefinitionsFactory() {
		// TODO Auto-generated method stub
		return super.getDefinitionsFactory();
	}

	@Override
	public void init(ActionServlet servlet, ModuleConfig moduleConfig)
			throws ServletException {
		// TODO Auto-generated method stub
		super.init(servlet, moduleConfig);
	}

	@Override
	protected void initDefinitionsMapping() throws ServletException {
		// TODO Auto-generated method stub
		super.initDefinitionsMapping();
	}

	@Override
	protected void internalModuleRelativeForward(String uri,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.internalModuleRelativeForward(uri, request, response);
	}

	@Override
	protected void internalModuleRelativeInclude(String uri,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.internalModuleRelativeInclude(uri, request, response);
	}

	@Override
	protected void processForwardConfig(HttpServletRequest request,
			HttpServletResponse response, ForwardConfig forward)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.processForwardConfig(request, response, forward);
	}

	@Override
	protected boolean processTilesDefinition(String definitionName,
			boolean contextRelative, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return super.processTilesDefinition(definitionName, contextRelative, request,
				response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	protected void doInclude(String uri, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.doInclude(uri, request, response);
	}

	@Override
	protected MessageResources getInternal() {
		// TODO Auto-generated method stub
		return super.getInternal();
	}

	@Override
	protected ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return super.getServletContext();
	}

	@Override
	protected void log(String message) {
		// TODO Auto-generated method stub
		logger.error(message);
	}

	@Override
	protected void log(String message, Throwable exception) {
		logger.error(exception);
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.process(request, response);
	}

	@Override
	protected Action processActionCreate(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException {
		// TODO Auto-generated method stub
		return super.processActionCreate(request, response, mapping);
	}

	@Override
	protected ActionForm processActionForm(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping) {
		// TODO Auto-generated method stub
		return super.processActionForm(request, response, mapping);
	}

	@Override
	protected ActionForward processActionPerform(HttpServletRequest request,
			HttpServletResponse response, Action action, ActionForm form,
			ActionMapping mapping) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return super.processActionPerform(request, response, action, form, mapping);
	}

	@Override
	protected void processCachedMessages(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.processCachedMessages(request, response);
	}

	@Override
	protected void processContent(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.processContent(request, response);
	}

	@Override
	protected ActionForward processException(HttpServletRequest request,
			HttpServletResponse response, Exception exception, ActionForm form,
			ActionMapping mapping) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return super.processException(request, response, exception, form, mapping);
	}

	@Override
	protected boolean processForward(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		return super.processForward(request, response, mapping);
	}

	@Override
	protected boolean processInclude(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		return super.processInclude(request, response, mapping);
	}

	@Override
	protected void processLocale(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.processLocale(request, response);
	}

	@Override
	protected ActionMapping processMapping(HttpServletRequest request,
			HttpServletResponse response, String path) throws IOException {
		// TODO Auto-generated method stub
		return super.processMapping(request, response, path);
	}

	@Override
	protected HttpServletRequest processMultipart(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.processMultipart(request);
	}

	@Override
	protected void processNoCache(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.processNoCache(request, response);
	}

	@Override
	protected String processPath(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		return super.processPath(request, response);
	}

	@Override
	protected void processPopulate(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, ActionMapping mapping)
			throws ServletException {
		// TODO Auto-generated method stub
		super.processPopulate(request, response, form, mapping);
	}

	@Override
	protected boolean processPreprocess(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return super.processPreprocess(request, response);
	}

	@Override
	protected boolean processRoles(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		return super.processRoles(request, response, mapping);
	}

	@Override
	protected boolean processValidate(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, ActionMapping mapping)
			throws IOException, ServletException, InvalidCancelException {
		// TODO Auto-generated method stub
		return super.processValidate(request, response, form, mapping);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
