package com.dianping.data.warehouse.dao;

import com.dianping.data.warehouse.common.Const;
import com.dianping.data.warehouse.domain.InstanceDO;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by adima on 14-3-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-applicationcontext.xml")
public class InstanceDAOTest {
    @Resource(name="instanceDAO")
    private InstanceDAO dao;
    @Test
    public void testSaveInstance() throws Exception {

    }

    @Test
    public void testSaveInstanceRela() throws Exception {

    }

    @Test
    public void testGetInstanceId() throws Exception {
        String s = "1000120120509";
        String instanceId = dao.getInstanceId(s);
        Assert.assertEquals(instanceId,s);
    }

    @Test
    public void testGetReadyTaskList() throws Exception {
        List<InstanceDO> list = dao.getReadyTaskList(Const.JOB_STATUS.JOB_INIT.getValue(),123123242L);
        System.out.println(list.size());
        Assert.assertNotNull(list);
    }
    @Test
    public void testUpdateTaskReay() throws Exception {
        Integer flag = this.dao.updateTaskReady("1000120120514",Const.JOB_STATUS.JOB_READY.getValue());
        System.out.println(flag);
        Assert.assertEquals(flag,Integer.valueOf(1));
    }




}
