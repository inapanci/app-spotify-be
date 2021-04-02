package app.spotify.spotifybe.interceptor;

import app.spotify.spotifybe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String route = request.getRequestURL().toString();

        if (route.contains("spotify/login") || route.contains("spotify/register")) {
            return true;
        }
        if (request.getHeader("authorization-uuid") == null || request.getHeader("authorization-uuid").equals("")) {

            return false;
        }

        return userRepository.existsById(request.getHeader("authorization-uuid"));
    }

    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
    }

}
