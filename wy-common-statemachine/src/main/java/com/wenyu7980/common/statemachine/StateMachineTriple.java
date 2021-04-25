package com.wenyu7980.common.statemachine;

import com.wenyu7980.statemachine.StateMachine;

/**
 *
 * @author wenyu
 */
public interface StateMachineTriple<T, S1, S2, S3, E> extends StateMachine<T, StateContainerTriple<S1, S2, S3>, E> {
}
