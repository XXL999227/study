package cn.itcast.gateway.fliter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// @Order(-1) // 优先级, 数字越小, 优先级越高，这里用实现Ordered接口的方式代替
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    /**
     *  处理当前请求，有必要的话通过{@link GatewayFilterChain}将请求交给下一个过滤器处理
     *
     * @param exchange 请求上下文，里面可以获取Request、Response等信息
     * @param chain 过滤器链，用来把请求委托给下一个过滤器
     * @return {@code Mono<Void>} 返回标示当前过滤器业务结束
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求
        ServerHttpRequest request = exchange.getRequest();
        // 2.获取请求参数
        String authorization = request.getQueryParams().getFirst("authorization");
        // 3.判断是否有authorization参数
        if ("admin".equals(authorization)) {
            // 4.有, 放行
            return chain.filter(exchange);
        }
        // 5.没有, 设置状态码返回401
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
