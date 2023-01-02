package com.seamew.controller;

import com.alibaba.fastjson.JSONArray;
import com.seamew.entity.vo.UserVO;
import com.seamew.entity.vo.TestVO;
import com.seamew.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
// "user" 前的 "/" 可以省略
@RequestMapping("user")
@Slf4j
@SessionAttributes({"modelAttribute"})
public class UserController
{
    @RequestMapping
    (
        // url 请求路径
        path = "get1",
        // 只接受 GET 类型的请求
        method = RequestMethod.GET,
        // 只接受 header 中有 "test" 的请求
        headers = "test",
        // 只接受 contentType 为 "text/html" 类型的请求
        consumes = "text/html",
        // 返回的响应的 contentType 为 "application/json"
        produces = "application/json"
    )
    public String handleGetUser1(Model model)
    {
        // model 对象的作用域和 request 相似
        model.addAttribute("user", "lulu");
        return "user";
    }

    // @GetMapping 等价于 @RequestMapping(path = "save", method = RequestMethod.GET)
    // POST, PUT, DELETE, PATCH 等请求方法都有对应的注解
    @GetMapping("register")
    public String handleRegister()
    {
        return "register";
    }

    @PostMapping("save1")
    public String handleSaveUser1(HttpServletRequest request)
    {
        // 自动注入域对象 request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("The username is [{}]", username);
        log.info("The password is [{}]", password);
        return "register";
    }

    // @RequestParam("paramName") 将请求中的参数绑定到方法中的参数
    @PostMapping("save2")
    public String handleSaveUser2(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        // 自动注入请求中的参数 username 和 password
        log.info("The user name is [{}]", username);
        log.info("The user password is [{}]", password);
        return "register";
    }

    @PostMapping("save3")
    public String handleSaveUser3(UserVO userVO)
    {
        // 自动将请求中的参数 username 和 password 封装为 User 对象传入
        log.info("Save user [{}]", userVO);
        return "register";
    }

    @GetMapping("get2")
    public String handleGetUser2(@RequestHeader(value = "school", required = false) String school,
                                 HttpServletRequest req,
                                 HttpServletResponse resp,
                                 HttpSession session,
                                 Model model)
    {
        // 在域对象中设置属性
        req.setAttribute("requestAttribute", "request");
        // 告诉浏览器添加一个 cookie, 下次浏览器向该域名发送请求时在 header 中会携带 cookie
        resp.addCookie(new Cookie("cookieVal", "cookie"));
        session.setAttribute("sessionAttribute", "session");
        model.addAttribute("modelAttribute", "model");
        log.info("The school is [{}]", school);
        // 加上 "forward" 避免 spring 走视图解析器
        return "forward:get3";
    }

    @GetMapping("get3")
    public String handleGetUser3(@RequestAttribute(value = "requestAttribute", required = false) String reqAttr,
                                 @SessionAttribute(value = "sessionAttribute", required = false) String sessionAttr1,
                                 @SessionAttribute(value = "modelAttribute", required = false) String sessionAttr2,
                                 @CookieValue(value = "cookieVal", required = false) String cookieVal,
                                 @ModelAttribute(value = "modelAttribute") String modelAttr)
    {
        // 从域对象中获取属性
        log.info("The request attribute is [{}]", reqAttr);
        // 从 session 中获取属性
        log.info("The session attribute1 is [{}]", sessionAttr1);
        // 在类上面使用 @SessionAttributes({"name"}) 后, 如果我们在 model 中添加了名为
        // "name" 的属性, spring 会把这个属性同步到 session 域对象中, 可以实现不同请求的
        // 数据共享
        log.info("The session attribute2 is [{}]", sessionAttr2);
        // 从 cookie 中获取属性
        log.info("The cookie value is [{}]", cookieVal);
        // 从 model 对象中获取属性
        log.info("The model attribute is [{}]", modelAttr);
        return "user";
    }

    @PostMapping("array")
    public String handleUserArrayParam(@RequestParam("id") String[] ids)
    {
        // 封装请求中传递的参数数组 (都是同名参数, 例如 id=1&id=2, id=1,2)
        log.info("The array is [{}]", (Object) ids);
        return "success";
    }

    @GetMapping("get4")
    public String handleGetUser4()
    {
        return "vo";
    }

    @PostMapping("param")
    public String handleUserParam(TestVO testVO)
    {
        // 将表单发送的请求中的参数封装到 UserVO 对象中传入
        // 表单中 <input/> 标签的 name 属性要与 UserVO 中的属性相对应，和 SpEL 的语法一样
        log.info("The userVO is [{}]", testVO);
        return "success";
    }

    // 将 user 对象使用 fastjson 手动序列化为 json 字符串
    @GetMapping(value = "getAll1", produces = "application/json")
    // 将方法的返回值直接绑定在响应体中，不走视图解析器
    @ResponseBody
    public String handleGetAllUsers1()
    {
        List<UserVO> userVOS = List.of(
            new UserVO(1, "admin", "123", null, null, null),
            new UserVO(2, "Jack", "456", null, null, null)
        );
        return JSONArray.toJSONString(userVOS);
    }

    // 使用在 spring 中配置的 MessageConverter 将返回的对象自动序列化为 JSON 字符串
    @GetMapping(value = "getAll2", produces = "application/json")
    @ResponseBody
    public List<UserVO> handleGetAllUsers2()
    {
        return List.of(
            new UserVO(1, "admin", "123", null, null, null),
            new UserVO(2, "Jack", "456", null, null, null)
        );
    }

    // 当我们想要将请求体中的数据转化（反序列化）为一个 Java 对象时，我们可以在方法的参数上使用
    // @RequestBody 注解，spring 会使用相应的 HttpMessageConverter 完成这一转换工作。
    // 这里我们在容器中注入了一个 MappingJackson2HttpMessageConverter，它可以将前端发送
    // 的 JSON 字符串转化为一个普通的 Java 对象，详见 springmvc-servlet.xml。下面的例子
    // 把前端传来的 JSON 字符串转化为一个 User 对象
    @PostMapping("save4")
    public void handleSaveUser4(@RequestBody UserVO userVO)
    {
        log.info("Save user [{}]", userVO);
    }

    // user 中新增了一个 LocalDate 类型的 birthday 属性，当前端传来请求的 ContentType
    // 为 x-www-form-urlencoded，并且其中有字符串表示的的日期时，如果想要将字符串转化为
    // LocalDate 类型的对象，我们需要自己实现一个 Converter，然后在 spring 容器中注入一个
    // ConversionServiceFactoryBean，将我们自己写的 Converter 注入到它的 converters（Set）
    // 属性中，这样 spring 在尝试进行类型转换时，就会调用 converter 的 convert 方法
    @PostMapping("save5")
    public void handleSaveUser5(UserVO userVO)
    {
        log.info("Save user [{}]", userVO);
    }

    // 要将 requestParam 中表示日期的字符串转换为日期对象赋值给 user 的 birthday 属性时，
    // 除了通过向容器中注入 Converter 这样全局配置的方法外，我们还可以在 birthday 属性上添加
    // 一个 @DateTimeFormat 注解，指定它的 pattern 属性，spring 就会按照这个 pattern 将
    // 请求参数中的日期字符串转化为日期对象（反序列化），这个注解的效果和我们自己写 Converter
    // 实现的效果一样，只不过这种方法可以用作局部配置。这里有个问题，使用 @DateTimeFormat 无法
    // 将字符串转化为 LocalDate 或 LocalDateTime。
    @PostMapping("save6")
    public void handleSaveUser6(UserVO userVO)
    {
        log.info("Save user [{}]", userVO);
    }

    // 想要在 JSON 和日期对象间进行相互转换时，我们需要使用 jackson 来帮助我们进行转化工作。
    // 我们需要在日期字段上添加 @JsonFormat(pattern = ...) 来指定日期序列化和反序列化的格
    // 式。需要注意的是，@DateTimeFormat 只支持对 requestParam 中对日期反序列化，而 @JsonFormat
    // 同时支持日期序列化为 JSON 和 JSON 反序列化为日期。如果日期类型是 java8 以后的新特性，
    // 如 LocalDate，LocalDateTime，我们还需要添加 jackson-datatype-jsr310 这个依赖，
    // 并且在 CustomObjectMapper 中使用 registerModule() 方法注册 JavaTimeModule 来
    // 支持转化工作，否则只支持 Date 类型的序列化和反序列化
    @PostMapping("save7")
    public void handleSaveUser7(@RequestBody UserVO userVO)
    {
        log.info("Save user [{}]", userVO);
    }

    // 前端传来的数据通常是不可信的，我们要对数据进行校验，验证其是否满足我们的要求，例如用户名
    // 长度是否合法、年龄是否超过最大值等等。我们可以在相应的字段上加上 JSR303 提供的注解，详见
    // javax.validation.constraints 包下的注解。handler 方法中接收到前端传来的参数时，
    // Spring 会根据不同的注解来判断数据是否合法，这一工作是通过 HibernateValidator 来实现的，
    // 数据验证的封装在 BindingResult 中，我们可以通过它的 getAllErrors 方法获得所有的错误信息。
    // 作为扩展，HibernateValidator 还为我们提供了一些其它常用的注解，例如 @Email、@Length 等。
    // 要使数据验证生效，需要进行以下几个步骤:
    // 1. 引入 javax:validation:validation-api 和 org.hibernate.validator:hibernate-validator 两个依赖
    // 2. 在 springmvc-servlet.xml 配置 localValidator，并指定 providerClass 属性为 org.hibernate.validator.HibernateValidator
    // 3. 配置 springmvc-servlet.xml 中的 <mvc:annotation-driven validator="localValidator"/>
    // 4. 在需要进行验证的字段上添加相应的注解，在 handler 方法中需要验证的参数前加上 @Validated 注解
    @PostMapping("save8")
    public String handleSaveUser8(@Validated UserVO userVO, BindingResult br)
    {
        List<ObjectError> errors = br.getAllErrors();
        if (!errors.isEmpty())
        {
            log.info("用户信息保存失败");
            errors.forEach((e) -> log.info("{}", e.getDefaultMessage()));
            return "fail";
        }
        log.info("保存用户 [{}]", userVO);
        return "success";
    }

    // 通常 dao、service、controller 层并不处理异常，而是交给前端控制器来统一处理异常。
    // 通过阅读 DispatcherServlet 中 doDispatch() 方法源码，我们可以发现当 handler
    // 方法执行过程中出现异常时，spring 会尝试将异常交给 HandlerExceptionResolver 来
    // 统一进行处理。那么我们可以尝试自己写一个 HandlerExceptionResolver 的实现类注入到
    // 容器中，就如这里的 GlobalHandlerExceptionResolver。当然，想要进行全局异常处理还
    // 有更简单的方式，我们可以在 @Controller 或 @ControllerAdvice 类中定义处理异常的
    // 方法，在方法上使用 @ExceptionHandler 注解，在方法的参数中声明异常的类型，那么在出
    // 现相应异常时，spring 就会调用这个 @ExceptionHandler 方法来处理异常。在 @Controller
    // 和 @ControllerAdvice 中使用 @ExceptionHandler 的区别在于它们处理的异常的作用域
    // 不同，前者只处理 @Controller 类的 handler 方法中出现的异常，而后者则可以处理所有
    // @Controller 类的 handler 方法中的异常。在 @Controller 和 @ControllerAdvice
    // 类中，@ExceptionHandler 方法返回一个错误页面视图，我们也可以在 @RestControllerAdvice
    // 类中定义 @ExceptionHandler 方法，可以直接返回对象，并将对象利用 MessageConverter
    // 进行转化后放到响应体中。@RestControllerAdvice 拥有 @ResponseBody 和 @ControllerAdvice
    // 两个元注解
    // 详见 https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-exceptionhandler
    @GetMapping("exception")
    public String exception() throws BusinessException
    {
        throw new BusinessException("出现了业务异常");
    }

    // spring 的 interceptor 不同于 tomcat 的 filter，filter 是在给请求匹配 servlet 之前
    // 进行过滤，而 interceptor 是 DispatchServlet 在匹配 handler 方法之前进行拦截
    @GetMapping("interceptor")
    @ResponseBody
    public String interceptor()
    {
        return "interceptor";
    }
}
