package com.dianping.data.warehouse.executer;

import com.dianping.data.warehouse.common.Const;
import com.dianping.data.warehouse.dao.InstanceDAO;
import com.dianping.data.warehouse.domain.InstanceDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by adima on 14-3-23.
 */
public class ReadyExecuter {
    private static final Logger logger = LoggerFactory.getLogger(ReadyExecuter.class);

    @Resource(name = "instanceDAO")
    private InstanceDAO instDAO;

    public void execute(){
        List<InstanceDO> list = instDAO.getReadyTaskList(Const.JOB_STATUS.JOB_INIT.getValue(),System.currentTimeMillis());
        for(InstanceDO inst : list){
            try{
                this.updateTask(inst);
            }catch(Exception e){
                logger.error(inst.getInstanceId() + "(" + inst.getTaskName() + ") update ready error");
            }
        }
    }

    private boolean updateTask(InstanceDO inst){
        if(inst.getIfPre() == 0){
            Integer rtn = this.instDAO.updateTaskReady(inst.getInstanceId(),Const.JOB_STATUS.JOB_READY.getValue());
            return rtn ==1;
        }else if(inst.getIfPre() ==1){
            return true;
        }else{
            return false;
        }
    }
}
