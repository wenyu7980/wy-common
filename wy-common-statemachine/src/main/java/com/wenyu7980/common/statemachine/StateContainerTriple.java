package com.wenyu7980.common.statemachine;

import com.wenyu7980.statemachine.StateContainer;

/**
 * 三状态
 * @author wenyu
 */
public class StateContainerTriple<S1, S2, S3> implements StateContainer {
    private S1 s1;
    private S2 s2;
    private S3 s3;

    public StateContainerTriple(S1 s1, S2 s2, S3 s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public S1 getS1() {
        return s1;
    }

    public S2 getS2() {
        return s2;
    }

    public S3 getS3() {
        return s3;
    }

    @Override
    public boolean match(StateContainer s) {
        StateContainerTriple o = (StateContainerTriple) s;
        if (this.s1 != null && o.s1 != null && !this.s1.equals(o.s1)) {
            return false;
        }
        if (this.s2 != null && o.s2 != null && !this.s2.equals(o.s2)) {
            return false;
        }
        if (this.s3 != null && o.s3 != null && !this.s3.equals(o.s3)) {
            return false;
        }
        return true;
    }
}
