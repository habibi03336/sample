package com.hollysgang.sample.framework.core.manage.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-manage/")
public class APIPermissionController {

    private final APIPermissionService apiPermissionService;

    public APIPermissionController(APIPermissionService apiPermissionService) {
        this.apiPermissionService = apiPermissionService;
    }

    @GetMapping("/authorize-entity")
    public List<AuthorizeEntity> getAuthorizeEntities(){
        return apiPermissionService.getAuthorizeEntities();
    }

    @GetMapping("/api-info")
    public List<APIInfo> getAllApis(){
        return apiPermissionService.getAPIInfos("com.hollysgang.sample");
    }

    @GetMapping("/api-permission")
    public List<APIPermission> getAuthorizedApis(@RequestParam("authorizeEntity") String authorizeEntityKey){
        return apiPermissionService.getAuthorizedAPIInfos(authorizeEntityKey);
    }

    @PostMapping("/api-permission")
    public void addPermission(@RequestParam("authorizeEntity") String authorizeEntityKey, @RequestParam("apikey") String apikey){
        apiPermissionService.addPermission(authorizeEntityKey, apikey);
    }

    @DeleteMapping("/api-permission")
    public void delete(@RequestParam("authorizeEntity") String authorizeEntityKey, @RequestParam("apikey") String apikey){
        apiPermissionService.deletePermission(authorizeEntityKey, apikey);
    }

}
