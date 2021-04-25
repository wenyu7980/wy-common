package com.wenyu7980.common.statemachine;

import com.wenyu7980.statemachine.StateContainer;

/**
 *
 * @author wenyu
 */
public class StateContainerSingle<S> implements StateContainer {
    private S state;

    public StateContainerSingle(S state) {
        this.state = state;
    }

    public S getState() {
        return state;
    }

    @Override
    public boolean match(StateContainer s) {
        StateContainerSingle o = (StateContainerSingle) s;
        if (state == null || o.state == null) {
            return true;
        }
        return state.equals(o.state);
    }
}
