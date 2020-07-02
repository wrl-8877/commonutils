package com.example.mall.hander;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class AntiHandler implements HandlerInterceptor {

    @Autowired
    StringRedisTemplate redisTemplate;

    private String black = "anti::refresh::blacklist";
    private String ipPrefix = "anti::refersh::";
    private int stopTime = 30;
    private int blackTime = 100;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("pre");
        response.setContentType("text/html;charset=utf-8");
        String clientIp = request.getRemoteAddr();
        System.out.println(clientIp);

        String user = request.getHeader("User-Agent");
        String key = clientIp+"_"+user;

        System.out.println(key);

        String ip = ipPrefix+DigestUtils.md5DigestAsHex(key.getBytes());

        System.out.println(ip);

        boolean flag = redisTemplate.hasKey(black);
        //System.out.println(flag);
        if(flag) {
            if (redisTemplate.opsForSet().isMember(black, ip)) {
                response.getWriter().print("该ip已被加入黑名单");
                return false;
            }
        }
        Boolean hasKey = redisTemplate.hasKey(ip);
        if(hasKey){
            String s = redisTemplate.opsForValue().get(ip);
            Integer num = Integer.parseInt(s);
            if (num<stopTime) {
                redisTemplate.opsForValue().increment(ip, 1);
            }else if(num<blackTime){

                response.getWriter().print("访问次数过高，服务器已停止服务");
                redisTemplate.opsForValue().increment(ip, 1);
                return false;
            }else{
                response.getWriter().print("访问次数过高，该ip已被加入黑名单");
                redisTemplate.opsForSet().add(black,ip);
                redisTemplate.expire(black,1,TimeUnit.DAYS);
                log.error("{} : {}",black,ip);
                return false;
            }
        }else{
            redisTemplate.opsForValue().set(ip,1+"",1, TimeUnit.MINUTES);
        }
        log.error("{} 通过",ip);
        return true;
    }
}
