package org.ng12306.ngsql.parser.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public interface BinaryOperandCalculator {
    Number calculate(Integer integer1, Integer integer2);

    Number calculate(Long long1, Long long2);

    Number calculate(BigInteger bigint1, BigInteger bigint2);

    Number calculate(BigDecimal bigDecimal1, BigDecimal bigDecimal2);
}
