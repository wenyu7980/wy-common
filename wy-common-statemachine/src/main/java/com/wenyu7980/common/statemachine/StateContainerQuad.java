package com.wenyu7980.common.statemachine;

import com.wenyu7980.statemachine.StateContainer;

/**
 * 四状态
 * @author wenyu
 */
public class StateContainerQuad<S1, S2, S3, S4> implements StateContainer {
    private S1 s1;
    private S2 s2;
    private S3 s3;
    private S4 s4;

    public StateContainerQuad(S1 s1, S2 s2, S3 s3, S4 s4) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
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

    public S4 getS4() {
        return s4;
    }

    @Override
    public boolean match(StateContainer s) {
        StateContainerQuad o = (StateContainerQuad) s;
        if (this.s1 != null && o.s1 != null && !this.s1.equals(o.s1)) {
            return false;
        }
        if (this.s2 != null && o.s2 != null && !this.s2.equals(o.s2)) {
            return false;
        }
        if (this.s3 != null && o.s3 != null && !this.s3.equals(o.s3)) {
            return false;
        }
        if (this.s4 != null && o.s4 != null && !this.s4.equals(o.s4)) {
            return false;
        }
        return true;
    }
}
