//package com.biocv.boot.auth;
//
//import com.biocv.boot.auth.domain.bo.AuthPermissionBo;
//import com.biocv.boot.auth.service.AuthPermissionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.lang.reflect.Method;
//import java.util.Map;
//import java.util.Set;
//
///**
// * @author kai
// * @date 2021/2/2 18:44
// */
//@Component
//public class AuthInit implements ApplicationListener<ContextRefreshedEvent> {
//
//    @Autowired
//    private AuthPermissionService authPermissionService;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        ApplicationContext applicationContext = event.getApplicationContext();
//        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RestController.class);
//        Set<Map.Entry<String, Object>> entrySet = beansWithAnnotation.entrySet();
//        for (Map.Entry<String, Object> entry:entrySet){
//            Class<?> aClass = entry.getValue().getClass();
//            Method[] methods = aClass.getMethods();
//            for (Method method : methods){
//                PreAuthorize annotation = method.getAnnotation(PreAuthorize.class);
//                if (annotation != null){
//                    String value = annotation.value();
////            @PreAuthorize("hasAuthority('staff:staff:delete')")
//                    String authorization = value.substring(value.indexOf("'", value.lastIndexOf("'")));
//                    String[] split = authorization.split(":");
//                    String module = split[0];
//                    String menu = split[1];
//                    String api = split[2];
//
//                    AuthPermissionBo authPermissionBo = authPermissionService.getByCode(module);
//                    if (authPermissionBo == null){
//                        authPermissionBo = new AuthPermissionBo();
//                        authPermissionBo.setCode(module);
//                        authPermissionBo.setName("模块");
//                    }
//                    authPermissionService.save();
//                }
//            }
//
//        }
//    }
//}
