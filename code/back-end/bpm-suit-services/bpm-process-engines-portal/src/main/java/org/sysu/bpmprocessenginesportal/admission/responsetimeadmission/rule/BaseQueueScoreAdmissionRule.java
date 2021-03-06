package org.sysu.bpmprocessenginesportal.admission.responsetimeadmission.rule;


import org.sysu.bpmprocessenginesportal.admission.responsetimeadmission.IRTLScheduler;
import org.sysu.bpmprocessenginesportal.admission.responsetimeadmission.RTLScheduler;
import org.sysu.bpmprocessenginesportal.admission.responsetimeadmission.queuecontext.LinkedBlockingDelayQueueContext;
import org.sysu.bpmprocessenginesportal.requestcontext.ExecuteRequestContext;
import org.sysu.bpmprocessenginesportal.requestcontext.IRequestContext;

//基于延迟队列评分的准入算法
//
public class BaseQueueScoreAdmissionRule extends AbstractAdmissionAdmissionRule {

    private RTLScheduler RTLScheduler;
    private int rtl1 = 1; //表示rtl 1下最多延迟2t，也就是最多就放到第二个延迟队列上
    private int rtl2 = 3;


    public BaseQueueScoreAdmissionRule() {

    }

    public BaseQueueScoreAdmissionRule(IRTLScheduler admissionor) {
        this();
        this.RTLScheduler = (RTLScheduler) admissionor;
        setAdmissionor(admissionor);
    }

    @Override
    public void admit(IRequestContext requestContext) {
        ExecuteRequestContext executeRequestContext = (ExecuteRequestContext) requestContext;
        //计算之后放入到相应的队列中;
        //因为目前是调度层的消费大于准入层的生产，所以不用考虑执行队列

        if(executeRequestContext.getRtl().equals("0")) {
           this.RTLScheduler.getExecuteQueueContext().offer(requestContext);
        } else {
            int index = calculateIndex(executeRequestContext.getRtl());
            long expectExecuteTime = executeRequestContext.getStartTime() + (index + 1) * this.RTLScheduler.getTimeSlice();
            executeRequestContext.setExpectExecuteTime(expectExecuteTime);
            this.RTLScheduler.getDelayQueueContexts()[index].offer(requestContext);
        }
    }

    public int calculateIndex(String rtl) {
        int index = 0;
        if(rtl.equals("1")) {
            index = rtl1; //rtl表示最多延迟2个时间片
        } else if(rtl.equals("2")) {
            index = rtl2;
        }
        double maxScore = Double.NEGATIVE_INFINITY;
        int maxIndex = -1;
        double score;
        for(int i = 0; i < index; i++) {
            //i+1是因为执行队列才是0，延迟队列是从1开始
            score = calculateScore((LinkedBlockingDelayQueueContext) this.RTLScheduler.getDelayQueueContexts()[i], i + 1);
            if(maxScore < score) {
                maxScore = score;
                maxIndex = i;
            }
            System.out.println("score + i: " + score + "+" + i);
        }
        System.out.println("maxIndex: " + maxIndex);
        return maxIndex;
    }

    private double calculateScore(LinkedBlockingDelayQueueContext linkedBlockingDelayQueueContext, int index) {
        int size = linkedBlockingDelayQueueContext.getDelayQueue().size();
        return (this.RTLScheduler.getAverageHistoryRequestNumber() - size) * (1.0 / (index + 1));
    }

    public String getDelayQueueContextClassName() {
        return "LinkedBlockingDelayQueueContext";
    }

    public String getExecuteQueueClassName() {
        return "LinkedBockingExecuteQueueContext";
    }

}
