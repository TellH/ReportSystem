package com.tlh.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharSetFilter implements Filter {
         private FilterConfig conf ;
         @Override
         public void doFilter(ServletRequest req, ServletResponse resp,
                        FilterChain chain) throws IOException, ServletException {
                HttpServletRequest request=(HttpServletRequest) req;
                HttpServletResponse response=(HttpServletResponse) resp ;
                String charSet=conf .getInitParameter("CharSet");
                 request.setCharacterEncoding(charSet );
                 response.setCharacterEncoding(charSet );
                 response.setContentType("text/html;charset=" +charSet);
                 chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request ), response);
        }
         @Override
         public void init(FilterConfig filterConfig) throws ServletException {
                 this.conf =filterConfig;
        }
         @Override
         public void destroy() {}
         //��װ��
         private class MyHttpServletRequest extends HttpServletRequestWrapper{
                 private HttpServletRequest requst ;
                 public MyHttpServletRequest(HttpServletRequest request) {
                         super(request );
                         this.requst =request;
                }
                 @Override
                 public String getParameter(String name ) {
                        String value=this .requst.getParameter( name);
                         if(value ==null){
                                 return null ;
                        }
                         //�������get����
                         if(!this .requst.getMethod().equalsIgnoreCase( "get")){
                                 return value ;
                        }
                         try {
                                 value=new String(value.getBytes("ISO8859-1" ),this. requst.getCharacterEncoding());
                                 return value ;
                        } catch (UnsupportedEncodingException e ) {
                                 throw new RuntimeException(e);
                        }
                }
        }
}