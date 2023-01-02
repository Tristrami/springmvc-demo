package com.seamew.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver
{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        Class<? extends Throwable> exClass = ex.getClass();
        ModelAndView mv = new ModelAndView();
        if (BusinessException.class.isAssignableFrom(exClass))
        {
            // ...
            log.error("{}", ex.getMessage(), ex);
            mv.setViewName("error");
            mv.addObject("msg", ex.getMessage());
        }
        else if (Exception.class.isAssignableFrom(exClass))
        {
            // ...
            log.error("{}", ex.getMessage(), ex);
            mv.setViewName("error");
            mv.addObject("msg", ex.getMessage());
        }
        else if (Throwable.class.isAssignableFrom(exClass))
        {
            // ...
            log.error("{}", ex.getMessage(), ex);
            mv.setViewName("error");
            mv.addObject("msg", ex.getMessage());
        }
        else
        {
            // ...
            log.error("{}", ex.getMessage(), ex);
            mv.setViewName("error");
            mv.addObject("msg", ex.getMessage());
        }
        return mv;
    }
}
