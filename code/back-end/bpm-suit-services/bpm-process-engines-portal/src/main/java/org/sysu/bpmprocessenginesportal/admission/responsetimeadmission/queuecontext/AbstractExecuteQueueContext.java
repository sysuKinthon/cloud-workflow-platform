package org.sysu.bpmprocessenginesportal.admission.responsetimeadmission.queuecontext;

import org.sysu.bpmprocessenginesportal.requestcontext.IRequestContext;

import java.util.Queue;

public abstract class AbstractExecuteQueueContext implements IQueueContext {
    protected Queue<IRequestContext> executeQueue;

    public Queue<IRequestContext> getExecuteQueue() {
        return executeQueue;
    }

    public void setExecuteQueue(Queue<IRequestContext> executeQueue) {
        this.executeQueue = executeQueue;
    }
}
