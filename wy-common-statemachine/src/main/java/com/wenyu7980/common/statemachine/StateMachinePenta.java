package com.wenyu7980.common.statemachine;

import com.wenyu7980.statemachine.StateMachine;

/**
 *
 * @author wenyu
 */
public interface StateMachinePenta<T, S1, S2, S3, S4, S5, E>
  extends StateMachine<T, StateContainerPenta<S1, S2, S3, S4, S5>, E> {
}
