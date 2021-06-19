package com.wenyu7980.common.statemachine;

public interface StateMachineHandler<ID, D, E extends Enum<E>> {
    /**
     * 转换
     * @param id
     * @param event
     * @param context
     * @return
     */
    D sendEvent(ID id, E event, Object context);
}
