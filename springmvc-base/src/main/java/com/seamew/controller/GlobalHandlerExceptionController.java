package com.seamew.controller;

import com.seamew.exception.AccountException;
import com.seamew.exception.BusinessException;
import com.seamew.exception.OrderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalHandlerExceptionController
{
    // 在方法的参数中声明 BusinessException，但在 @ExceptionHandler 中可以声明只接受
    // AccountException 和 OrderException 这两种 BusinessException
    @ExceptionHandler({AccountException.class, OrderException.class})
    public ModelAndView resolveBusinessException(BusinessException ex)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("msg", ex.getMessage());
        return mv;
    }
}
