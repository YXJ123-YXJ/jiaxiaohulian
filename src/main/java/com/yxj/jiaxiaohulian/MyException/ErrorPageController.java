package com.yxj.jiaxiaohulian.MyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorPageController implements ErrorController {

    private static ErrorPageController errorPageController;

    @Autowired
    private ErrorAttributes errorAttributes;

    private final static String ERROR_PATH = "/error";

    public ErrorPageController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    public ErrorPageController() {
        if (errorPageController == null) {
            errorPageController = new ErrorPageController(errorAttributes);
        }
    }

    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if (HttpStatus.BAD_REQUEST==status||HttpStatus.UNAUTHORIZED==status||HttpStatus.NOT_FOUND == status||
                HttpStatus.PAYMENT_REQUIRED == status||HttpStatus.FORBIDDEN == status||HttpStatus.METHOD_NOT_ALLOWED == status
                ||HttpStatus.NOT_ACCEPTABLE == status||HttpStatus.PROXY_AUTHENTICATION_REQUIRED == status||HttpStatus.REQUEST_TIMEOUT == status
                ||HttpStatus.CONFLICT == status||HttpStatus.GONE == status||HttpStatus.LENGTH_REQUIRED == status
                ||HttpStatus.PRECONDITION_FAILED == status||HttpStatus.PAYLOAD_TOO_LARGE == status||HttpStatus.REQUEST_ENTITY_TOO_LARGE == status
                ||HttpStatus.URI_TOO_LONG == status||HttpStatus.REQUEST_URI_TOO_LONG == status||HttpStatus.UNSUPPORTED_MEDIA_TYPE == status
                ||HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE == status||HttpStatus.EXPECTATION_FAILED == status||HttpStatus.I_AM_A_TEAPOT == status
                ||HttpStatus.INSUFFICIENT_SPACE_ON_RESOURCE == status||HttpStatus.METHOD_FAILURE == status||HttpStatus.DESTINATION_LOCKED == status
                ||HttpStatus.UNPROCESSABLE_ENTITY == status||HttpStatus.LOCKED == status||HttpStatus.FAILED_DEPENDENCY == status
                ||HttpStatus.TOO_EARLY == status||HttpStatus.UPGRADE_REQUIRED == status||HttpStatus.PRECONDITION_REQUIRED == status
                ||HttpStatus.TOO_MANY_REQUESTS == status||HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE == status||HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS == status) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error/error_4xx");
            mav.addObject("status",status);
//            return new ModelAndView("error/4xx").addObject("status",status);
            return mav;
        } /*else if (HttpStatus.NOT_FOUND == status) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error/error_4xx");
            mav.addObject("status",status);
//            return new ModelAndView("error/4xx");
            return mav;
        }*/ else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error/error_5xx");
            mav.addObject("status",status);
//            return new ModelAndView("error/error_5xx");
            return mav;
        }
    }

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
        HttpStatus status = getStatus(request);
        return new ResponseEntity<Map<String, Object>>(body, status);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }


    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
