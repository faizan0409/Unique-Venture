package com.ozone.util; 

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




public class ControllerSupport {
	
	/**
	 * Initialize logger instance
	 */
	//private static final Logger LOGGER = Logger.getLogger(ControllerSupport.class.getName());

	private HttpServletRequest request;
	private HttpSession session;
	private int sessionUserid;
	private String username;
	private String sessionkey;

	private HttpServletResponse response;
	private ServletContext context;
	private String tabkey;
	private String tabflag;
	private String applicationVersion;


	protected int validateUserSes(HttpSession session)  {
		int intVal = 0;

		try {

			if (session.getAttribute("username") == null) {
				//LOGGER.warn("No active session found");
				return 1;
			}

			if (checkUserSession(session)) {
				if (this.getRequest().getUserPrincipal() != null) {
					this.getSession().setAttribute("username", this.getRequest().getUserPrincipal().getName());
					
				} else {
					//LOGGER.debug("No Authentication Cookie Found");
					
				}
			
			}
		} catch (NullPointerException e) {
			//LOGGER.warn("No active session found");
			// 
		} catch (Exception e) {

		}
		return 0;
	}

	private boolean checkUserSession(HttpSession session) {
		if (session == null || session.getAttribute("isUserPermitted") == null) {
			return false;
		}
		return true;
	}

	

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public int getSessionUserid() {
		return sessionUserid;
	}

	public void setSessionUserid(int sessionUserid) {
		this.sessionUserid = sessionUserid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionkey() {
		return sessionkey;
	}

	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public String getTabkey() {
		return tabkey;
	}

	public void setTabkey(String tabkey) {
		this.tabkey = tabkey;
	}

	public String getTabflag() {
		return tabflag;
	}

	public void setTabflag(String tabflag) {
		this.tabflag = tabflag;
	}

}
