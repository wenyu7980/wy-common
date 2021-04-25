package com.wenyu7980.common.statemachine;

import com.wenyu7980.statemachine.StateContainer;

/**
 *
 * @author wenyu
 */
public class StateContainerBinary<S1, S2> implements StateContainer {
    private S1 s1;
    private S2 s2;

    public StateContainerBinary(S1 s1, S2 s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public S1 getS1() {
        return s1;
    }

    public S2 getS2() {
        return s2;
    }

    @Override
    public boolean match(StateContainer s) {
        StateContainerBinary o = (StateContainerBinary) s;
        if (this.s1 != null && o.s1 != null && !this.s1.equals(o.s1)) {
            return false;
        }
        if (this.s2 != null && o.s2 != null && !this.s2.equals(o.s2)) {
            return false;
        }
        return true;
    }
}
