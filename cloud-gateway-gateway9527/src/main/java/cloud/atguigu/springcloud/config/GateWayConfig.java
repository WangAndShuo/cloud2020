package cloud.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator coutomrouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route(
                "path_luyou_atguitu",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei")
        ).build();
        return  routes.build();
    }


    @Bean
    public RouteLocator coutomrouteLocator2(RouteLocatorBuilder routeLocatorBuilder){

        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();

        builder.route("path_luyou_atguigu2",r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();

        return builder.build();

    }
}
