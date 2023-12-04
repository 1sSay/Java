package expression;

import java.math.BigInteger;

public class Multiply extends Operation {
    public Multiply(final AllExpression value1, final AllExpression value2) {
        super(value1, value2);
    }

    @Override
    public int hashCode() {
        return value1.hashCode() + value2.hashCode() * 107;
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return value1.toString() + " * " + value2.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder result = new StringBuilder();

        if (value1.isAdditive()) {
            result.append('(').append(value1.toMiniString()).append(')');
        } else {
            result.append(value1.toMiniString());
        }

        result.append(" * ");

        if (value2.isAdditive() || value2.isMultiplicative() && !value2.isCommutative()) {
            result.append('(').append(value2.toMiniString()).append(')');
        } else {
            result.append(value2.toMiniString());
        }

        return result.toString();
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

    @Override
    public boolean isMultiplicative() {
        return true;
    }

    @Override
    public boolean isAdditive() {
        return false;
    }

    @Override
    public int evaluate(int x) {
        return value1.evaluate(x) * value2.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value1.evaluate(x, y, z) * value2.evaluate(x, y, z);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return value1.evaluate(x).multiply(value2.evaluate(x));
    }

//    @Override
//    public BigInteger evaluate(BigInteger x, BigInteger y, BigInteger z) {
//        return value1.evaluate(x, y, z).multiply(value2.evaluate(x, y, z));
//    }
}
