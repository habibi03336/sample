package com.hollysgang.sample.framework.security.authorization.evaluator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;


class PermissionEvaluatorTest {

    @Test
    @DisplayName("ACCESS 케이스")
    public void testAccess(){
        // given
        APIPermissionLoader apiPermissionLoader = () -> {
                APIPermissionInfo apiPermissionInfo = new APIPermissionInfo("/api/local-case", HttpMethod.GET);
                apiPermissionInfo.addAllowedRoles("ROLE_LOCAL_INVESTIGATOR");
                return List.of(apiPermissionInfo);
        };
        PermissionEvaluator permissionEvaluator = new PermissionEvaluator(apiPermissionLoader);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/local-case");
        request.setMethod(HttpMethod.GET.name());

        // execute
        PermissionResult pr = permissionEvaluator.evaluate(request, "ROLE_LOCAL_INVESTIGATOR");

        // expect
        assertThat(pr).isEqualTo(PermissionResult.ACCESS);
    }

    @Test
    @DisplayName("DENY 케이스")
    public void testDenied(){
        // given
        APIPermissionLoader apiPermissionLoader = () -> {
            APIPermissionInfo apiPermissionInfo = new APIPermissionInfo("/api/nation-case", HttpMethod.GET);
            apiPermissionInfo.addAllowedRoles("ROLE_HEADQUARTER_INVESTIGATOR");
            return List.of(apiPermissionInfo);
        };
        PermissionEvaluator permissionEvaluator = new PermissionEvaluator(apiPermissionLoader);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/nation-case");
        request.setMethod(HttpMethod.GET.name());

        // execute
        PermissionResult pr = permissionEvaluator.evaluate(request, "ROLE_LOCAL_INVESTIGATOR");

        // expect
        assertThat(pr).isEqualTo(PermissionResult.DENIED);
    }


}