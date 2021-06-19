package com.wenyu7980.common.statemachine;

import com.wenyu7980.statemachine.StateMachine;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

public abstract class AbstractStateMachineHandler<ID, D, T, E extends Enum<E>>
  implements StateMachineHandler<ID, D, E> {
    private final StateMachine<T, E> stateMachine;
    private final Function<ID, T> findById;
    private final Function<T, D> convert;
    private final Function<T, T> save;

    public AbstractStateMachineHandler(StateMachine<T, E> stateMachine, Function<ID, T> findById,
      Function<T, D> convert, Function<T, T> save) {
        this.stateMachine = stateMachine;
        this.findById = findById;
        this.convert = convert;
        this.save = save;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public D sendEvent(ID id, E event, Object context) {
        T entity = findById.apply(id);
        this.stateMachine.sendEvent(entity, event, context);
        return convert.apply(save.apply(entity));
    }
}
