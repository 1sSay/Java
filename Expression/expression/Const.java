package expression;

import java.math.BigInteger;

public class Const implements AllExpression, NumberProperty {
    final Number value;

    public Const(final int value) {
        this.value = value;
    }

    public Const(final BigInteger value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String toMiniString() {
        return value.toString();
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

    @Override
    public boolean isMultiplicative() {
        return false;
    }

    @Override
    public boolean isAdditive() {
        return false;
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return (BigInteger)value;
    }

//    @Override
//    public BigInteger evaluate(BigInteger x, BigInteger y, BigInteger z) {
//        return (BigInteger)value;
//    }
}