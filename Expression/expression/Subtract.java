package expression;

import java.math.BigInteger;

public class Subtract extends Operation {
    public Subtract(final AllExpression value1, final AllExpression value2) {
        super(value1, value2);
    }

    @Override
    public int hashCode() {
        return value1.hashCode() + value2.hashCode() * 73;
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return value1.toString() + " - " + value2.toString();
    }

    @Override
    public String toMiniString() {
        if (value2.isAdditive()) {
            return value1.toMiniString() + " + (" + value2.toMiniString() + ")";
        } else {
            return value1.toMiniString() + " + " + value2.toMiniString();
        }
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

    @Override
    public boolean isMultiplicative() {
        return false;
    }

    @Override
    public boolean isAdditive() {
        return true;
    }

    @Override
    public int evaluate(int x) {
        return value1.evaluate(x) - value2.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value1.evaluate(x, y, z) - value2.evaluate(x, y, z);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return value1.evaluate(x).subtract(value2.evaluate(x));
    }

//    public BigInteger evaluate(BigInteger x, BigInteger y, BigInteger z) {
//        return value1.evaluate(x, y, z).subtract(value2.evaluate(x, y, z));
//    }
}
