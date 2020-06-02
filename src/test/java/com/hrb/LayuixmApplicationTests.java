package com.hrb;

import com.hrb.entity.SysPermission;
import com.hrb.service.SysPermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class LayuixmApplicationTests {
@Resource
 private SysPermissionService sysPermissionService;
    @Test
    void contextLoads() {

        List<SysPermission> yiji = sysPermissionService.findYiji(26);
        for (SysPermission yi:yiji){
            System.out.println(yi.getId()+"\t"+yi.getTitle());
        }
    }

}
