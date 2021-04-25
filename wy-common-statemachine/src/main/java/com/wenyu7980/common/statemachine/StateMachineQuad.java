package com.wenyu7980.common.statemachine;

import com.wenyu7980.statemachine.StateMachine;

/**
 *
 * @author wenyu
 */
public interface StateMachineQuad<T, S1, S2, S3, S4, E> extends StateMachine<T, StateContainerQuad<S1, S2, S3, S4>, E> {
}
