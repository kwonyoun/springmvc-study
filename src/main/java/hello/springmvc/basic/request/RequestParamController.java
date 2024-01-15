package hello.springmvc.basic.request;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requstParam1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //이거 추가시 @RestController와 같음
    @RequestMapping("/request-param-v2")
    public String requestParam2(@RequestParam("username") String memberName,    
                                @RequestParam("age") int memberAge){
        log.info("useranme={}, age={}",memberName, memberAge);
        return "ok";
    }

    @ResponseBody //이거 추가시 @RestController와 같음
    @RequestMapping("/request-param-v3")
    public String requestParam3(@RequestParam String memberName,    
                                @RequestParam int age){
        log.info("useranme={}, age={}",memberName, age);
        return "ok";
    }

    @ResponseBody //이거 추가시 @RestController와 같음
    @RequestMapping("/request-param-v4")
    public String requestParam4(String username,    
                                int age){
        log.info("useranme={}, age={}",username, age);
        return "ok";
    }

    @ResponseBody //이거 추가시 @RestController와 같음
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,    
    @RequestParam(required = false) Integer age){
        /**
         * required = false 는 파라미터가 없어도 된다.
         * required = true 는 값이 있어야한다. 아니면 오류발생. 빈값(공백)도 들어올수있음 주의!
         */
        log.info("useranme={}, age={}",username, age);
        return "ok";
    }

    @ResponseBody //이거 추가시 @RestController와 같음
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,    
    @RequestParam(required = false, defaultValue = "-1") Integer age){
        /**
         * required = false 는 파라미터가 없어도 된다.
         * required = true 는 값이 있어야한다. 아니면 오류발생. 빈값(공백)도 들어올수있음 주의!
         */
        log.info("useranme={}, age={}",username, age);
        return "ok";
    }

    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}
