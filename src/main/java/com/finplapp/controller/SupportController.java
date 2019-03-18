package com.finplapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SupportController {

    @RequestMapping("favicon.ico")
    public String favicon(@RequestParam int ver) {
        switch (ver) {
            case 0:
                return "forward:/static/images/favicon/main.ico";
            case 1:
                return "forward:/static/images/favicon/administrator.ico";
            case 2:
                return "forward:/static/images/favicon/analyst.ico";
            default:
                return "forward:/static/images/favicon/error.ico";
        }
    }

    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public String errorPage(HttpServletRequest httpRequest, ModelMap model) {

        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = " - Bad Request";
                break;
            }
            case 401: {
                errorMsg = " - Unauthorized";
                break;
            }
            case 404: {
                errorMsg = " - Resource not found";
                break;
            }
            case 500: {
                errorMsg = " - Internal Server Error";
                break;
            }
        }
        model.addAttribute("errorMsg", errorMsg);
        model.addAttribute("errCode", httpErrorCode);
        return "errorPage";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

}