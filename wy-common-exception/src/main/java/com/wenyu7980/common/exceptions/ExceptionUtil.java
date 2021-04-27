package com.wenyu7980.common.exceptions;

import com.wenyu7980.common.exceptions.code400.BadBodyException;
import com.wenyu7980.common.exceptions.code401.InsufficientException;
import com.wenyu7980.common.exceptions.code403.LoginFailException;
import com.wenyu7980.common.exceptions.code403.TokenExpiredException;
import com.wenyu7980.common.exceptions.code404.NotFoundException;
import com.wenyu7980.common.exceptions.code409.ExistedException;
import com.wenyu7980.common.exceptions.code409.InconsistentException;
import com.wenyu7980.common.exceptions.code500.SystemException;
import com.wenyu7980.decisiontable.DecisionTable;

import java.util.function.BiFunction;

import static com.wenyu7980.decisiontable.DecisionMatches.EQ;

/**
 *
 * @author wenyu
 */
public class ExceptionUtil {
    private static final DecisionTable<BiFunction<String, String, Throwable>> TABLE = DecisionTable.of();

    static {
        TABLE.add((v1, v2) -> new BadBodyException(v1), EQ(400), EQ(BadBodyException.CODE));
        TABLE.add((v1, v2) -> new InsufficientException(v1), EQ(401), EQ(InsufficientException.CODE));
        TABLE.add((v1, v2) -> new LoginFailException(v1), EQ(403), EQ(LoginFailException.CODE));
        TABLE.add((v1, v2) -> new TokenExpiredException(v1), EQ(403), EQ(TokenExpiredException.CODE));
        TABLE.add((v1, v2) -> new NotFoundException(v1), EQ(404), EQ(NotFoundException.CODE));
        TABLE.add((v1, v2) -> new ExistedException(v1), EQ(409), EQ(ExistedException.CODE));
        TABLE.add((v1, v2) -> new InconsistentException(v1), EQ(409), EQ(InconsistentException.CODE));
        TABLE.add((v1, v2) -> new SystemException(v1), EQ(500), EQ(SystemException.CODE));
    }

    private ExceptionUtil() {
    }

    public static Throwable getThrowable(ErrorResponseBody body) {
        return TABLE.get(body.getCode(), body.getCode()).orElse((v1, v2) -> new SystemException(v1))
          .apply(body.getMessage(), null);
    }
}
