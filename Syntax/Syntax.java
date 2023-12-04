public class Syntax {
    public static void main(String args[]) {
        // [final] variable [= initializer];
        // var variable = initializer;
        // primitives:
        // Integers
        //  int - 32 bits       Integer
        //  long - 64           Long
        //  char - 16           Character
        //  byte - 8            Byte
        //  short - 16 0        Short

//        int dec = 10;
//        int hex = 0x10;
//        int oct = 010;
//        int bin = 0b10;
//        int underline = 0b1000_1000;
//        long l = 123456789123L;

        // Floating Point Number
        // float - 32 bits      Float
        // double - 64 bits     Double
//        float f = 0.0f;
//        double d = 0.0d;
//        double d = 0xa.123p2;

        // 0xD800..DBFF -> 1024
        // 0xDC00..DFFF -> 1024

        // Logic
        // boolean - 1 bit      Boolean
        // void - 0             Void

        // Priority
        // ( ... ), [ ... ], f( ... ), new Type( ... )
        // Postfix              -- ++
        // Prefix               -- ++ + - ~ ! (cast)
        // Multiplicative       * / %
        // Additive             + -
        // Shifts               << >> >>>
        // Relational           < > <= >= instanceof
        // Equality             == !=
        // Bitwise AND          &
        // Bitwise OR           |
        // Bitwise XOR          ^
        // Logical AND          &&
        // Logical OR           ||
        // Ternary              cond ? if-true : if-false
        // Assignment           = @= (+ - * / % << >> >>> & | ^)

        // Statements
        //  Empty               ;
        // Block                {}
        //      Variable declaration
        // Expressions
        //      Assignment
        //      Inc/Dec;
        //      Call
        // If-then-else         if (cond) if-true [else if-false]
        // Switch-block         switch (variable) case @: ... break default: ... break (fallthrough)
        // Loops
        //      while
        //      do ... while
        //      for
        //      for-each
        //      continue
        //      break
        //      labels
        //      try-catch-throwable
        //      throw
        //      return
    }
}
