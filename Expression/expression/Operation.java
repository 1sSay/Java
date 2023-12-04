package expression;

import expression.AllExpression;
import expression.BigIntegerExpression;

public abstract class Operation implements AllExpression {
    final AllExpression value1;
    final AllExpression value2;

    public Operation(final AllExpression value1, final AllExpression value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}
