/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempreatureporject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 *
 * @author abbasalshaqaq
 */
public interface ExecutorService {

    public ScheduledExecutorService scheduledExecutorServiceChart = Executors.newSingleThreadScheduledExecutor();
    ;
    public ScheduledExecutorService scheduledExecutorServiceLastTemp = Executors.newSingleThreadScheduledExecutor();
;

}
