package expression;

import java.math.BigInteger;
import java.util.Objects;

public class Variable implements AllExpression {
    final String name;

    public Variable(final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toMiniString() {
        return name;
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
    public int evaluate(int x) throws RuntimeException {
        if (name == "x") {
            return x;
        }

        throw new RuntimeException("Wrong variable name");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x" -> {
                return x;
            }
            case "y" -> {
                return y;
            }
            case "z" -> {
                return z;
            }
        }

        throw  new RuntimeException("Wrong variable name");
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        if (Objects.equals(name, "x")) {
            return x;
        }

        throw new RuntimeException("Wrong variable name");
    }

//    @Override
//    public BigInteger evaluate(BigInteger x, BigInteger y, BigInteger z) {
//        switch (name) {
//            case "x" -> {
//                return x;
//            }
//            case "y" -> {
//                return y;
//            }
//            case "z" -> {
//                return z;
//            }
//        }
//
//        throw  new RuntimeException("Wrong variable name");
//    }
}
